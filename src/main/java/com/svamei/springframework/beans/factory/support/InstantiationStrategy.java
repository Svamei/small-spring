package com.svamei.springframework.beans.factory.support;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;

}
