package com.nit.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class EnvCheckAspect {


    @Pointcut("@annotation(com.nit.anno.EnvCheck)")
    public void envCheck(){}


    @Around("envCheck()")
    public Object envCheckAround(ProceedingJoinPoint point) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String env = request.getHeader("env");
        if(Objects.equals(env,"online")){
//            NimConf.setOnlineNim();
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
//            NimConf.setDefaultNim();
        }
    }
}
