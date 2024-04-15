package com.example.YouAndMe.aspect;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogginAspect {

    @Before("execution(* com.example.YouAndMe.controller.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinpoint) {
        Logger logger = LoggerFactory.getLogger(joinpoint.getClass());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            Enumeration<String> parameterNames = request.getParameterNames();
            List<String> parameters = Collections.list(parameterNames)
                    .stream()
                    .map(name -> name + request.getParameter(name))
                    .collect(Collectors.toList());
            logger.info("{}: {}, parameters={{}}", method, url, String.join(",", parameters));
        }
    }
}
