package com.svamei.springframework.aop.framework;

import com.svamei.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JdkDynamicAopProxy
 * @Description
 * @Author Svamei
 * @Date 14:04 2023/3/18
 **/
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                advisedSupport.getTargetSource().getTargetClass(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (advisedSupport.getMethodMatcher()
                .matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            MethodInterceptor interceptor = advisedSupport.getInterceptor();
            return interceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }
}