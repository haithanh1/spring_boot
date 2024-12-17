package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimingAspect {
    private long startTime;

//    @Before("execution(* com.example.service.address.AddressService.calculateFactorial(..))")
//    public void beforeCalculate(JoinPoint joinPoint) {
//        System.out.println(joinPoint.getSignature().getModifiers());
//        System.out.println(joinPoint.getArgs()[1]);
//        System.out.println(joinPoint.getTarget());
//
//        System.out.println(joinPoint.getThis()+"fsafsag");
//
//
//    }
}
