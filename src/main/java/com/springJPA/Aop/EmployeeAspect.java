package com.springJPA.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class EmployeeAspect {
	
	private final Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.springJPA.controllers.EmployeeController.*(..))")
	public void monitorMethods(JoinPoint jp) {
		log.info("method completed  successfully"+jp.getSignature());
		
	}


}
