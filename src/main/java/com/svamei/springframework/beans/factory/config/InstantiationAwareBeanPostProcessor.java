package com.svamei.springframework.beans.factory.config;

import com.svamei.springframework.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException;

}