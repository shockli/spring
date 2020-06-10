package com.example.restservice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.example.restservice.GreetingController)")
    public void stringProcessingMethods() {
    };

    @After("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
                .getName();

        logger.log(Level.INFO, "название метода: " + methodName);
    }

    //вот так почему то не стабатывает триггер
    //AfterReturning(pointcut = "execution(public String com.example.restservice.GreetingController.*(..))", returning = "result")
    @AfterReturning(pointcut = "stringProcessingMethods()", returning = "greeting")
    public void logAfterReturning(JoinPoint joinPoint, Greeting greeting) {
        logger.log(Level.INFO, "возвращенное значение: " + greeting.getContent());
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.log(Level.INFO, joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }
}
