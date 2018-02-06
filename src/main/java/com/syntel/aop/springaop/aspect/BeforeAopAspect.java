package com.syntel.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class BeforeAopAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//what kind of method calls i would intercept
	//execution (* PACKAGE.*.*(..))
	//			ANY RETURN TYPE in a SPECIFIC PACKAGE.Any class.any method irrespective of arguments
	//Weaving and Weaver
	@Before("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(JoinPoint joinpoint) {
		// Advice -What to do?
		//
		logger.info("Before Method call:Intercepted a method call{}",joinpoint);
	}
}
