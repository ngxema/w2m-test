package com.w2m.superheroes.timecontrol;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectPerformance {

    private static final Log log = LogFactory.getLog(AspectPerformance.class);

    @Around("execution(* *(..)) && @annotation(performanceOn)")
    public Object trace(ProceedingJoinPoint pjp, PerformanceOn performanceOn) throws Throwable {

        Object resultado = null;
        Throwable throwable = null;
        long initTime = System.currentTimeMillis();

        try {
            resultado = pjp.proceed();
        } catch ( Throwable t ) {
            throwable = t;
        }

        long endTime = System.currentTimeMillis();
        long spentTime = endTime - initTime;
        log.info("Performance time on: " + pjp.getSignature() + " -> " + spentTime);

        if ( throwable == null ) return resultado;
        throw throwable;
    }
}