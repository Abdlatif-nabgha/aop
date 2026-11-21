package com.bank.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class LoggingAspect {
    long t1, t2;
    @Pointcut("execution(* metier.*.*(..))")
    public void pc1(){}

    @Before("pc1()")
    public void before(JoinPoint joinPoint) {
        t1 = System.currentTimeMillis();
        System.out.println("-----------------------");
        System.out.println("Before executing methode: "+ joinPoint.getSignature());
    }

    @After("pc1()")
    public void after(JoinPoint joinPoint) {
        System.out.println("After method execution: " + joinPoint.getSignature());
        t2 = System.currentTimeMillis();
        System.out.println("Time execution of the method: "+ (t2-t1));
        System.out.println("-----------------------");
    }
}
