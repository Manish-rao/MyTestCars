package com.auto.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class MyCustomAspect {

	
	@Before("execution(* com.auto..controller..*(..)) ||\r\n" + 
			"execution(* com.auto..service..*(..)) ||\r\n" + 
			"execution(* com.auto..repo..*(..))")
	public void before(JoinPoint joinPoint) {
        log.info("******Target class******: " + joinPoint.getTarget().getClass().getName());
        log.info("****Method name****: " + joinPoint.getSignature().getName());
    }
}
