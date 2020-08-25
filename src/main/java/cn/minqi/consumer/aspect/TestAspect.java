package cn.minqi.consumer.aspect;

import cn.minqi.consumer.annotation.TestAnnotation;
import cn.minqi.consumer.majorService.MoonService;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MinQi create_time 2019-07-04 17:03
 **/

@Slf4j
@Aspect
@Component
public class TestAspect {

    @Autowired
    private MoonService moonService;

    @Pointcut("@annotation(cn.minqi.consumer.annotation.TestAnnotation)")
    public void aspect() {
    }


    @Around(value = "aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取拦截的方法名
        Signature sig = joinPoint.getSignature();
        //获取拦截的方法名
        MethodSignature methodSignature = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass()
            .getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //获取注解信息
        TestAnnotation annotation = currentMethod.getAnnotation(TestAnnotation.class);

        String key = annotation.value();
        int count = annotation.count();
        double timeout = Double.parseDouble(annotation.timeout());
        log.info("value: [{}]  count :[{}] timeout : [{}]", key, count, timeout);

        try {
            moonService.testAnnotation(key, count, timeout);
            return joinPoint.proceed();
        } catch (Exception re) {
            re.printStackTrace();
            return joinPoint.proceed();
        }
    }
}



