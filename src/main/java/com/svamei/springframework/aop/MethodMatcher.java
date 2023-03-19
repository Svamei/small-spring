package com.svamei.springframework.aop;

import java.lang.reflect.Method;

/**
 * @ClassName MethodMatcher
 * @Description
 * @Author Svamei
 * @Date 12:06 2023/3/18
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}