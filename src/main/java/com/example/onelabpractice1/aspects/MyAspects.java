package com.example.onelabpractice1.aspects;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
public class MyAspects {

    private final Logger logger = Logger.getLogger(MyAspects.class);

    @Pointcut("execution(* com.example.onelabpractice1.controllers.AuthController.*(..))")
    private void AuthControllerLog() { }

    @Before("AuthControllerLog()")
    public void logBefore(JoinPoint joinPoint) {
        BasicConfigurator.configure();

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        logger.debug("Entering in Method :  " + joinPoint.getSignature().getName());
        logger.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        logger.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        logger.debug("Target class : " + joinPoint.getTarget().getClass().getName());

        logger.debug("Start Header Section of request ");
        logger.debug("Method Type : " + request.getMethod());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            logger.debug("Header Name: " + headerName + " Header Value : " + headerValue);
        }
        logger.debug("Request Path info :" + request.getServletPath());
        logger.debug("End Header Section of request ");
    }

    @AfterReturning(value = "AuthControllerLog()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = result.toString();
        logger.debug("Method Return value : " + returnValue);
    }

    @AfterThrowing(value = "AuthControllerLog()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        logger.error("Cause : " + exception.getCause());
    }

    @Around("AuthControllerLog()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.debug("Method " + className + "." + methodName + " ()" + " execution time : "
                    + elapsedTime + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    @Before("@annotation(com.example.onelabpractice1.aspects.GetInfoBeforeExecute)")
    public void beforeAspect(JoinPoint joinPoint) {
        System.out.println("METHOD " + joinPoint.getSignature().getName() +
                " FROM " + joinPoint.getSignature().getDeclaringTypeName() +  " STARTS EXECUTING");
    }

    @AfterThrowing (value = "@annotation(com.example.onelabpractice1.aspects.ExceptionChecker)", throwing = "ex")
    public void ExceptionChecker(JoinPoint joinPoint, Exception ex) {
        System.out.println(("EXCEPTION IS DEFINED IN '" + joinPoint.getSignature().getName() +
                "' METHOD, EXCEPTION: " + ex));
    }
}
