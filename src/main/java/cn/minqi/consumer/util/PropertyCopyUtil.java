package cn.minqi.consumer.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 基于reflectasm高性能反射工具包的属性复制实现，支持NULL过滤特性。
 *
 * @author minqi
 */
@Slf4j
public final class PropertyCopyUtil {
    private static Map<Class, MethodAccess> CLASS_METHOD_MAP = new HashMap<>();
    private static Map<String, Integer> METHOD_INDEX_MAP = new HashMap<>();
    private static Map<Class, List<String>> CLASS_FIELD_MAP = new HashMap<>();

    private PropertyCopyUtil() {

    }

    /**
     * 复制源对象实例属性到目标对象实例
     *
     * @param sourceObj 源对象实例
     * @param target    目标对象实例
     */
    public static void copyProperties(Object sourceObj, Object target) {
        MethodAccess sourceMethodAccess = getMethodAccess(sourceObj);
        MethodAccess targetMethodAccess = getMethodAccess(target);

        List<String> fieldList = CLASS_FIELD_MAP.get(sourceObj.getClass());
        for (String field : fieldList) {
            Integer setIndex = obtainSetIndex(target.getClass(), field);
            if (setIndex != null) {
                // 属性存在
                Integer getIndex = obtainGetIndex(sourceObj.getClass(), field);
                if (getIndex != null) {
                    Object sourceValue = sourceMethodAccess.invoke(sourceObj, getIndex);
                    if (sourceValue != null) {
                        // 如果源值是NULL，默认是不复制的
                        targetMethodAccess.invoke(target, setIndex.intValue(), sourceValue);
                    }
                }
            }
        }
    }

    /**
     * 从源对象复制属性到目标对象类，并自动生成实例
     *
     * @param sourceObj   源对象实例
     * @param targetClass 目标对象类
     * @param <T>         类定义
     * @return 复制了属性的目标对象实例。如果源对象为NULL，则返回NULL，如果复制失败，则抛出异常
     */
    public static <T> T copyProperties(Object sourceObj, Class<T> targetClass) {
        if (sourceObj == null) {
            return null;
        }
        T t;
        try {
            t = targetClass.newInstance();
        } catch (Exception e) {
            log.error("属性复制失败", e);
            throw new RuntimeException(e);
        }

        copyProperties(sourceObj, t);
        return t;
    }

    /**
     * 自动复制集合类对象实例属性到目标类，并自动生成列表对象实例
     *
     * @param sourceObjs  源对象实例列表
     * @param targetClass 目标对象类
     * @param <T>         目标类范型
     * @return 复制了源对象相同属性的目标对象实例列表。如果源对象为NULL，则返回NULL，如果复制失败，则抛出异常
     */
    public static <T> List<T> copyCollectionProperties(Iterable<?> sourceObjs, Class<T> targetClass) {
        if (sourceObjs == null || targetClass == null) {
            return null;
        }

        T t;
        List<T> targets = new ArrayList<>();
        for (Object o : sourceObjs) {
            try {
                t = targetClass.newInstance();
                copyProperties(o, t);
                targets.add(t);
            } catch (Exception e) {
                log.error("属性复制失败", e);
                throw new RuntimeException(e);
            }
        }

        return targets;
    }

    private static MethodAccess getMethodAccess(Object obj) {
        MethodAccess targetMethodAccess = CLASS_METHOD_MAP.get(obj.getClass());
        if (targetMethodAccess == null) {
            targetMethodAccess = cache(obj);
        }

        return targetMethodAccess;
    }

    private static MethodAccess cache(Object obj) {
        Class<?> objClass = obj.getClass();
        synchronized (objClass) {
            MethodAccess methodAccess = MethodAccess.get(objClass);
            List<Field> fields = ReflectionUtil.getFields(objClass);
            List<String> fieldList = new ArrayList<>(fields.size());
            for (Field field : fields) {
                if (!Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                    try {
                        //非公共私有变量
                        String fieldName = StringUtils.capitalize(field.getName());
                        cacheGetMethod(objClass, methodAccess, fieldName);
                        cacheSetMethod(objClass, methodAccess, fieldName);
                        fieldList.add(fieldName);
                    } catch (Exception ignore) {
                        log.debug("cache {} exception: {}", objClass, ignore.getMessage());
                    }
                }
            }
            CLASS_FIELD_MAP.put(objClass, fieldList);
            CLASS_METHOD_MAP.put(objClass, methodAccess);
            return methodAccess;
        }
    }

    private static void cacheGetMethod(Class<?> objClass, MethodAccess methodAccess, String fieldName) {
        try {
            int index = methodAccess.getIndex("get" + fieldName);
            METHOD_INDEX_MAP.put(objClass.getName() + "." + "get" + fieldName, index);
        } catch (Exception iae) {
            try {
                // 可能是boolean类型
                int index = methodAccess.getIndex("is" + fieldName);
                METHOD_INDEX_MAP.put(objClass.getName() + "." + "is" + fieldName, index);
            } catch (Exception ignore) {
                log.debug("cacheGetMethod [{}] failed: {}", objClass, ignore.getMessage());
            }
        }
    }

    private static void cacheSetMethod(Class<?> objClass, MethodAccess methodAccess, String fieldName) {
        try {
            int index = methodAccess.getIndex("set" + fieldName);
            METHOD_INDEX_MAP.put(objClass.getName() + "." + "set" + fieldName, index);
        } catch (Exception ignore) {
            log.debug("cacheSetMethod [{}] failed: {}", objClass, ignore.getMessage());
        }
    }

    private static Integer obtainGetIndex(Class<?> objClass, String fieldName) {
        String getKey = objClass.getName() + "." + "get" + fieldName;
        Integer index = METHOD_INDEX_MAP.get(getKey);
        if (index == null) {
            getKey = objClass.getName() + "." + "is" + fieldName;
            index = METHOD_INDEX_MAP.get(getKey);
        }

        return index;
    }

    private static Integer obtainSetIndex(Class<?> objClass, String fieldName) {
        String getKey = objClass.getName() + "." + "set" + fieldName;
        Integer index = METHOD_INDEX_MAP.get(getKey);
        return index;
    }
}
