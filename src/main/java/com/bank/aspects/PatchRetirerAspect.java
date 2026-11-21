package com.bank.aspects;

import com.bank.metier.Account;
import com.bank.metier.IMetierImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class PatchRetirerAspect {

    @Pointcut("execution(* com.bank.metier.IMetierImpl.retirer(..))")
    public void pc1() {}

    @Around("pc1() && args(code, sold)")
    public Object around(Long code, double sold, ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
        IMetierImpl metier = (IMetierImpl) joinPoint.getTarget();
        Account account = metier.getAccount(code);
        if (account.getSolde() < sold) {
            throw new IllegalArgumentException("Sold insufficient");
        }
        return proceedingJoinPoint.proceed();
    }
}
