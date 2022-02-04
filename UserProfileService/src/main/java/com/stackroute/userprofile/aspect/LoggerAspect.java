package com.stackroute.userprofile.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggerAspect {
	private static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

	@Before("execution(* com.stackroute.userprofile.controller.UserProfileController.*(..))")
	public void executedMethodsLogger(JoinPoint joinPoint) {
		LOG.info("[ Executed method {} ]", joinPoint.toString());
	}

	@AfterReturning(value = "execution(* com.stackroute.userprofile.controller.UserProfileController.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		LOG.info("{} returned with value {}", joinPoint, result);
	}

	@After(value = "execution(* com.stackroute.userprofile.controller.UserProfileController.*.*(..))")
	public void after(JoinPoint joinPoint) {
		LOG.info("after execution of {}", joinPoint);
	}
	
	@AfterThrowing(value = "execution(* com.stackroute.userprofile.controller.UserProfileController.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) {
		LOG.info("after throwing of {}", joinPoint);
	}
}
