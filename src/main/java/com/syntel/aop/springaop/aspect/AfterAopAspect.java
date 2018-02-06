package com.syntel.aop.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterAopAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(ProceedingJoinPoint joinpoint) throws Throwable {
		//start time=x
		//allow execution of the method
		//end time=y
		long startTime=System.currentTimeMillis();
		joinpoint.proceed();
		long timeTaken=System.currentTimeMillis()-startTime;
		logger.info("Time taken by {} is {}", joinpoint,timeTaken);
	}
}
