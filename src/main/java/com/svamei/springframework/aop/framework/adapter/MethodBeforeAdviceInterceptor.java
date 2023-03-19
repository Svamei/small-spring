package com.svamei.springframework.aop.framework.adapter;

import com.svamei.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @ClassName MethodBeforeAdviceInterceptor
 * @Description
 * @Author Svamei
 * @Date 20:36 2023/3/18
 **/
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }


    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        this.advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}