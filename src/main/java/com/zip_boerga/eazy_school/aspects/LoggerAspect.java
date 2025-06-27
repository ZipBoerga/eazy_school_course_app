package com.zip_boerga.eazy_school.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@Aspect
public class LoggerAspect {
    @Around("execution(* com.zip_boerga.eazy_school..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String signature = joinPoint.getSignature().toString();
        log.info("{} method execution start", signature);
        Instant start = Instant.now();
        Object returnObject = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("{}: execution ended in {} ms", signature, timeElapsed);
        return returnObject;
    }

    @AfterThrowing(value = "execution(* com.zip_boerga.eazy_school..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("{}: an exception happened due to: {}", joinPoint.getSignature().toString(), ex.getMessage());
    }
}
