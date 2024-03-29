package cn.minqi.consumer.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * 反射工具类
 *
 * @author minqi
 */
@Slf4j
public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    /**
     * 根据类生成对象实例
     *
     * @param cls 类
     * @param <T> 范型定义
     * @return 类实例
     */
    public static <T> T newInstance(Class<T> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            log.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return (T) instance;
    }

    /**
     * 执行某个对象实例的方法
     *
     * @param object 对象实例
     * @param method 方法
     * @param args   方法参数
     * @return 执行返回结果
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Exception e) {
            log.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置对象实例字段的值
     *
     * @param object 对象实例
     * @param field  字段
     * @param value  值
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            log.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取对象实例的字段值
     *
     * @param field  字段
     * @param object 对象实例
     * @return 值
     */
    public static Object getField(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            log.error("get field failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过反射获取类的所有字段列表， 包括父级
     *
     * @param clazz 类
     * @return 字段列表
     */
    public static List<Field> getFields(Class clazz) {
        List<Field> result = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Class superclass = clazz.getSuperclass();
        if (superclass != null) {
            addSuperclassFields(result, superclass);
        }
        return result;
    }

    private static void addSuperclassFields(List<Field> result, Class superclass) {
        List<Field> superclassFields = getFields(superclass);
        List<String> resultNames = result.stream().map(Field::getName).collect(Collectors.toList());
        List<Field> validSuperclassFields = superclassFields.stream().filter(superclassField -> !resultNames.contains(superclassField.getName())).collect(Collectors.toList());
        result.addAll(validSuperclassFields);
    }

    /**
     * 返回表示此类型实际类型参数的 Type 对象的数组
     *
     * @param type 类型
     * @return Type 对象的数组
     */
    public static Type[] getActualTypeArguments(Type type) {
        return ((ParameterizedType) type).getActualTypeArguments();
    }

    /**
     * 检查是否是基本类型或其包装类型
     *
     * @param type 类型
     * @return 如果是基本类型，则返回true，否则false
     */
    public static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive()
                || type.equals(Integer.class)
                || type.equals(String.class)
                || type.equals(Long.class)
                || type.equals(Double.class)
                || type.equals(Byte.class)
                || type.equals(Short.class)
                || type.equals(Float.class)
                || type.equals(Character.class);
    }
}
