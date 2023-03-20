package com.svamei.springframework.test.interceptor;

import com.svamei.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName UserServiceBeforeAdvice
 * @Description
 * @Author Svamei
 * @Date 21:54 2023/3/18
 **/
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("前拦截方法：" + method.getName());
    }
}