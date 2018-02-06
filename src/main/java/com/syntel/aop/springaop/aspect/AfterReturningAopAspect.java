package com.syntel.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterReturningAopAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(value = "execution (* com.syntel.aop.springaop.service.*.*(..))", returning = "result")
	public void after(JoinPoint joinpoint, Object result) {
		logger.info("{} returned with value {}", joinpoint, result);
	}
}
