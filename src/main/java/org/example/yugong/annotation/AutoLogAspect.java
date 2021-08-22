package org.example.yugong.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author qiaobao
 * @since 2021-06-04
 */

@Component
@Aspect
@Slf4j
public class AutoLogAspect {



    @Before("@annotation(org.example.yugong.annotation.AutoLog)")
    public void before(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            String methodExplain = null;
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(AutoLog.class)) {
                AutoLog annotation = method.getAnnotation(AutoLog.class);
                if (annotation != null) {
                    methodExplain = annotation.methodExplain();
                }
            }

            String name = method.getName();
            log.info("进入方法 methodName = {},methodExplain = {}", name, methodExplain);
            if (args == null || args.length == 0) {
                return;
            }
            for (Object arg : args) {
                if (arg == null) {
                    log.info("方法入参：null");
                } else {
                    log.info("方法入参：{}", args);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("打印方法日志异常");
        }

    }


}
