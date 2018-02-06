					Spring AOP

Spring AOP is to intercept the methos calls in an object.
1.	PointCut
2.	JoinCut
3.	Advice
4.	Aspect
5.	Viewing
6.	Viewer

Cross cutting concerns on all the layers. AOP is the best approach for implementing the cross cutting concerns.
	
Eg:-
//AOP
//Configuration
@Aspect
@Configuration
public class BeforeAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//what kind of method calls i would need to intercept
	//execution (* PACKAGE.*.*(..))
	//			ANY RETURN TYPE in a SPECIFIC PACKAGE.Any class.any method irrespective of arguments
	@Before("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(JoinPoint joinpoint) {
		//What to do?
		logger.info("Intercepted a method call{}",joinpoint);
	}
}

Point cut:
I would need to define which methods I would want to intercept called point cut.

Definition:
The expression which defines what kind of methods do I need to intercept Is called pointcut.

Eg:- @Before("execution (* com.syntel.aop.springaop.service.*.*(..))")

The expression (* PACKAGE.*.*(..)) is called pointcut
// ANY RETURN TYPE in a SPECIFIC PACKAGE.Any class.any method

Advice:
What should I do after intercepting a method is called. The logic defined to do something after intercepting a method is called Advice.

Eg:-
        public void before(JoinPoint joinpoint) {
	//What to do logic is called advice
	//Do this
	logger.info("Intercepted a method call{}",joinpoint);
	}


Aspect: 
The Combination of the pointcut and advice is called Aspect.
What kind of methods to intercept and what to do together called as an Aspect.

Eg:-
@Before("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(JoinPoint joinpoint) {
	//What to do?
	logger.info("Intercepted a method call{}",joinpoint);
	 }

JoinPoint:
The JoinPoint is a specific execution interception of a method call. This gives info about what has got intercepted

Eg:
public void before(JoinPoint joinpoint) {
		//What to do logic is called advice
		//Do this
		logger.info("Intercepted a method call{}",joinpoint);
	}


Viewing:
The process of implementing the AOP around the method calls is called viewing 

Viewer:
The framework which implements the viewing is called as viewer.

Types of aspects:
1.	Before
2.	AfterReturning
3.	AfterThrowing
4.	After
5.	Around

•	Before
        @Before("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(JoinPoint joinpoint) {
	// Advice -What to do?
	//
	logger.info("Before Method call:Intercepted a method call{}",joinpoint);
	}

•	AfterReturning
Eg:-
        @AfterReturning(value = "execution (* com.syntel.aop.springaop.service.*.*(..))", returning = "result")
	public void after(JoinPoint joinpoint, Object result) {
		logger.info("{} returned with value {}", joinpoint, result);
	}

•	AfterThrowing
Eg:-
        @AfterThrowing(value = "execution (* com.syntel.aop.springaop.service.*.*(..))", throwing = "exception")
	public void after(JoinPoint joinpoint, Object exception) {
		logger.info("{} returned with value {}", joinpoint, exception);
	}

•	After
Eg:-

        @Before("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void before(JoinPoint joinpoint) {
		logger.info("After a method call{}", joinpoint);
		}

•	Around
Eg:-
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

Note:

Problem 1:
The above pointcuts are repeating for all types of aspects for every method. Imagine if it is a big application and it becomes more complex and to avoid one have to follow the industry standards.


Solution:
We need to have the separate files defining all the point cuts 

Eg:-
package com.syntel.aop.springaop.aspect.commanconfig;

import org.aspectj.lang.annotation.Pointcut;

public class CommanJoinPointConfig {

	@Pointcut("execution (* com.syntel.aop.springaop.dao.*.*(..))")
	public void dataLayerExecution() {
		
	}
}

Q) How to use the commanJoinPointConfig ?

A) 
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


The other type of poincuts are 

1)	allLayerExecution 
2)	beanContatingDao
3)	dataLayerExecutionWithWithin



public class CommanJoinPointConfig {

	@Pointcut("execution (* com.syntel.aop.springaop.dao.*.*(..))")
	public void dataLayerExecution() {}
	
	@Pointcut("execution (* com.syntel.aop.springaop.service.*.*(..))")
	public void serviceLayerExecution() {}
	
	@Pointcut("com.syntel.aop.springaop.aspect.commanconfig.CommanJoinPointConfig.dataLayerExecution() &&       com.syntel.aop.springaop.aspect.commanconfig.CommanJoinPointConfig.serviceLayerExecution()")
	public void allLayerExecution() {}
	
	@Pointcut("bean(*dao*)")
	public void beanContatingDao() {}
	
	@Pointcut("within(com.syntel.aop.springaop.dao..*)")
	public void dataLayerExecutionWithWithin() {}
	
}

Q) Implement an annotation which actually calculates the perforamce of a particular class and/or chosen methods
A)@TrackTime

Eg:-

package com.syntel.aop.springaop.dao.aspect.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Methods
//Annotation info should be available at Run time

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {

}

Q) How to define in the commonConfigClass

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


Q) How to use this custom annotation?

package com.syntel.aop.springaop.dao;

import org.springframework.stereotype.Service;

import com.syntel.aop.springaop.dao.aspect.customannotation.TrackTime;

@Service
public class Dao1 {

	@TrackTime
	public String retrieveSomething() {
		return  "Dao1";
	}
	
}

==============End=========================




