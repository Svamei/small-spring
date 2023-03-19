package com.svamei.springframework.test.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @ClassName UserServiceInterceptor
 * @Description
 * @Author Svamei
 * @Date 14:59 2023/3/18
 **/
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = invocation.proceed();
        } finally {
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称：" + invocation.getMethod());
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End\r\n");
        }
        return result;
    }
}