package com.syntel.aop.springaop.aspect.commanconfig;

import org.aspectj.lang.annotation.Pointcut;

public class CommanJoinPointConfig {

	@Pointcut("execution (* com.syntel.aop.springaop.dao.*.*(..))")
	public void dataLayerExecution() {}
	
	@Pointcut("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void serviceLayerExecution() {}
	
	@Pointcut("com.syntel.aop.springaop.aspect.commanconfig.CommanJoinPointConfig.dataLayerExecution() && com.syntel.aop.springaop.aspect.commanconfig.CommanJoinPointConfig.serviceLayerExecution()")
	public void allLayerExecution() {}
	
	@Pointcut("bean(*dao*)")
	public void beanContatingDao() {}
	
	@Pointcut("within(com.syntel.aop.springaop.dao..*)")
	public void dataLayerExecutionWithWithin() {}
	
	@Pointcut("@annotation(com.syntel.aop.springaop.dao.aspect.customannotation.TrackTime)")
	public void trackTimeAnnotation() {}

}
