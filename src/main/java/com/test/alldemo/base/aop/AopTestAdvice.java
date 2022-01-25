package com.test.alldemo.base.aop;

import com.test.alldemo.common.AopTest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author admin
 */
@Component
@Aspect
public class AopTestAdvice {



    @Around("@annotation(aopTest)")
    public Object around(ProceedingJoinPoint point, AopTest aopTest){
        System.out.println(Arrays.toString(point.getArgs()));
        Signature signature = point.getSignature();
        System.out.println(signature.getDeclaringTypeName());
        System.out.println(point.getTarget());
        System.out.println(point.getThis());
        System.out.println(aopTest.key());
        Object proceed = null;
        try {
             proceed = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return proceed;
    }

}
