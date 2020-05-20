package com.medoshin.lectures.javalab.springcore.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Aspect
@Component
public class LogAspect {
    @PostConstruct
    public void initIt() {
//        System.out.println("logAspect");
    }

    @Pointcut("execution(* com.medoshin.lectures.javalab.springcore.component.AOPSandbox.doAspect(..))")
    public void doAspect() {
    }

    @Before("doAspect()")
    public void beforeAdvice() {
        System.out.println("----------before advice--------");
    }

    @After("doAspect()")
    public void afterAdvice() {
        System.out.println("----------after advice--------");
    }
}
