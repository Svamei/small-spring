package com.svamei.springframework.aop;

/**
 * @ClassName Pointcut
 * @Description
 * @Author Svamei
 * @Date 12:03 2023/3/18
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}