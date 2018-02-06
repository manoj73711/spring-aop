package com.syntel.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterThrowingAopAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterThrowing(value = "execution (* com.syntel.aop.springaop.service.*.*(..))", throwing = "exception")
	public void after(JoinPoint joinpoint, Object exception) {
		logger.info("{} returned with value {}", joinpoint, exception);
	}
}
