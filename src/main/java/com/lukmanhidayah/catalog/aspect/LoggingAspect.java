package com.lukmanhidayah.catalog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.lukmanhidayah.catalog.dto.BookDetailResponseDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

  @Pointcut("execution(* com.lukmanhidayah.catalog.web.*.*(..))")
  private void restAPI() {

  }

  @Pointcut("within(com.lukmanhidayah.catalog.web.*)")
  private void withPointCutExample() {

  }

  @Pointcut("args(com.lukmanhidayah.catalog.dto.publisher.PublisherCreateRequestDto)")
  private void argsPointCutExample() {

  }

  @Pointcut("@args(com.lukmanhidayah.catalog.annotation.LogThisArg)")
  private void argsAnnotationPointCutExample() {

  }

  @Pointcut("@annotation(com.lukmanhidayah.catalog.annotation.LogThisMethod)")
  private void annotationPointCutExample() {

  }

  @Before("annotationPointCutExample()")
  public void beforeExecutionLogging() {
    log.info("This is before execution from annotation");
  }

  @After("annotationPointCutExample()")
  public void afterExecutionLogging() {
    log.info("This is after execution from annotation");
  }

  @AfterReturning("annotationPointCutExample()")
  public void afterReturningExecutionLogging() {
    log.info("This is after returning execution from annotation");
  }

  @AfterThrowing("annotationPointCutExample()")
  public void afterThrowingExecutionLogging() {
    log.info("This is after throwing execution from annotation");
  }

  @Around("restAPI()")
  public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    try {
      log.info("start {}.{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
      stopWatch.start();

      return joinPoint.proceed();

    } finally {
      stopWatch.stop();

      log.info("finish {}.{} in: {} ms", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(),
          stopWatch.getTotalTimeMillis());
    }
  }

}
