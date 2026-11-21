package com.bank.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.bank.metier.*.*(..))")
    public void pc1() {}

    @Around("pc1()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        System.out.println("-----------------------");
        System.out.println("Before executing methode: "+ joinPoint.getSignature());
        try {
            return joinPoint.proceed();
        } finally {
            System.out.println("After method execution: " + joinPoint.getSignature());
            System.out.println("Time execution of the method: "
                    + ((System.nanoTime() - start) / 1_000_000) + " ms");
            System.out.println("-----------------------");
        }
    }
}
