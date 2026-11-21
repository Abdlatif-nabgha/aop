package com.bank.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@Aspect
public class LoggingAspect {
    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }
    @Pointcut("execution(* com.bank.metier.*.*(..))")
    public void pc1() {}

    @Around("pc1()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        logger.info("-----------------------");
        logger.info("Before executing methode: "+ joinPoint.getSignature());
        try {
            return joinPoint.proceed();
        } finally {
            logger.info("After method execution: " + joinPoint.getSignature());
            logger.info("Time execution of the method: "
                    + ((System.nanoTime() - start) / 1_000_000) + " ms");
            logger.info("-----------------------");
        }
    }
}
