package org.project.springhometask8.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserActionLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserActionLoggerAspect.class);

    @Before("@annotation(org.project.springhometask8.aspect.TrackUserAction)")
    @Order(1)
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        logger.info("Method called: {}.{}() with arguments {}", className, methodName, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "@annotation(org.project.springhometask8.aspect.TrackUserAction)", returning = "result")
    @Order(2)
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringTypeName();

        logger.info("Method finished: {}.{}() with result {}", className, methodName, result);
    }
}
