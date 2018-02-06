package com.syntel.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AroundAopAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(JoinPoint joinpoint) {
		logger.info("After a method call{}", joinpoint);
	}
}
