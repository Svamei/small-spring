package com.svamei.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @ClassName AdvisedSupport
 * @Description
 * @Author Svamei
 * @Date 14:00 2023/3/18
 **/
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor interceptor;

    private MethodMatcher methodMatcher;

    private boolean proxyTargetClass;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}