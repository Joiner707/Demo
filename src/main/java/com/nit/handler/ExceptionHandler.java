package com.nit.handler;

import com.nit.util.Response;
import com.nit.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.nit.controller")
@ResponseBody
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Object allExceptionHandler(HttpServletRequest request,
                                      Exception exception) throws Exception{
        String contextPath = request.getContextPath();

        //将异常对象写入日志内
        log.error(exception.getMessage(), exception);
        //返回异常信息, 直接返回给客户端
        return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR, "系统错误:"+exception.getMessage());
    }
}
