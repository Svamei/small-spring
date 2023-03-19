package com.svamei.springframework.aop;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
