package com.svamei.springframework.beans.factory.config;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.BeanFactory;

public interface AutoWireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
