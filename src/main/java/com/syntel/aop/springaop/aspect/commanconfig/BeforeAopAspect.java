package com.syntel.aop.springaop.aspect.commanconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class BeforeAopAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("com.syntel.aop.springaop.aspect.commanconfig.CommanJoinPointConfig.dataLayerExecution()")
	public void before(JoinPoint joinpoint) {
		logger.info("Before Method call:Intercepted a method call{}",joinpoint);
	}
}
