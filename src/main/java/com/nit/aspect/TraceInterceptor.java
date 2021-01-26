package com.nit.aspect;

import com.nit.anno.IdentifyCheck;
import com.nit.util.RequestIdGenerator;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class TraceInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {

        String traceId = RequestIdGenerator.getTraceId();
//        System.out.println("traceId:"+traceId);

        HandlerMethod handlerMethod = null;
        if (o instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) o;
        } else {
            return true;
        }

        Object bean = handlerMethod.getBean();
        Method method = handlerMethod.getMethod();
        IdentifyCheck identifyCheck = method.getAnnotation(IdentifyCheck.class);
        if(identifyCheck != null ) {
            if(identifyCheck.value() !=null) {
                MDC.put("traceId",traceId );
                return true;
            }
            else {
                return true;
            }
        }

        return true;
    }
}
