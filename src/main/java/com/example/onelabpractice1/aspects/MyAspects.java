package com.example.onelabpractice1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspects {

    @Pointcut("execution(* com.example.onelabpractice1.repository.UserRepository.save(..))")
    private void startEndNotify() { }

    @Around("startEndNotify()")
    public Object aroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("METHOD " + proceedingJoinPoint.getSignature().getName() +
                " STARTS");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("METHOD " + proceedingJoinPoint.getSignature().getName() +
                " ENDS");

        return object;
    }

    @Before("@annotation(com.example.onelabpractice1.aspects.GetInfoBeforeExecute)")
    public void beforeAspect(JoinPoint joinPoint) {
        System.out.println("METHOD " + joinPoint.getSignature().getName() +
                " FROM " + joinPoint.getSignature().getDeclaringTypeName() +  " STARTS EXECUTING");
    }

    @AfterReturning(value = "execution(* com.example.onelabpractice1.repository.CardRepository.getCardByNumber(..))",
            returning = "retVal")
    public void afterReturningAdvice(JoinPoint jp, Object retVal){
        System.out.println("Method Signature: "  + jp.getSignature());
        System.out.println("Returning:" + retVal.toString() );
    }

    @AfterThrowing (value = "@annotation(com.example.onelabpractice1.aspects.ExceptionChecker)", throwing = "ex")
    public void ExceptionChecker(JoinPoint joinPoint, Exception ex) {
        System.out.println(("EXCEPTION IS DEFINED IN '" + joinPoint.getSignature().getName() +
                "' METHOD, EXCEPTION: " + ex));
    }
}
