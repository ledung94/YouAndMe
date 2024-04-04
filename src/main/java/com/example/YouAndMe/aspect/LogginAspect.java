package com.example.YouAndMe.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {

    @Before("execution(* com.example.YouAndMe.controller.*.*(..))")
    public void beforeMethodExecution() {
        System.out.println("Method execution is started...");
    }
}
