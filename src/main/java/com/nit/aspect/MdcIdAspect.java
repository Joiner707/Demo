package com.nit.aspect;

import com.nit.anno.IdentifyCheck;
import com.nit.util.RequestIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class MdcIdAspect {


    @Pointcut("@annotation(com.nit.anno.IdentifyCheck)")
    public void trace(){}


    @Around("trace()")
    public Object traceAround(ProceedingJoinPoint point) {
        IdentifyCheck identifyCheck;
        StringBuilder traceId = new StringBuilder();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isAnnotationPresent(IdentifyCheck.class)) {
            identifyCheck = method.getDeclaringClass().getAnnotation(IdentifyCheck.class);
            traceId.append(identifyCheck.value()).append("::").append(RequestIdGenerator.getTraceId());
        }
        if (method.isAnnotationPresent(IdentifyCheck.class)) {
            identifyCheck = method.getAnnotation(IdentifyCheck.class);
            traceId.append(identifyCheck.value()).append("::").append(RequestIdGenerator.getTraceId());
        }
        MDC.put("traceId", traceId.toString());
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            MDC.clear();
        }
    }
}
