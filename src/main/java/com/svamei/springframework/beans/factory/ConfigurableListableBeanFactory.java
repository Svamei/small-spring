package com.svamei.springframework.beans.factory;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @ClassName ConfigurableListableBeanFactory
 * @Description
 * @Author Svamei
 * @Date 14:08 2023/3/7
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, BeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}