package com.svamei.springframework.aop;

/**
 * @ClassName ClassFilter
 * @Description
 * @Author Svamei
 * @Date 12:05 2023/3/18
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}