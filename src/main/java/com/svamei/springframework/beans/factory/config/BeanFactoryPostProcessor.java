package com.svamei.springframework.beans.factory.config;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @ClassName BeanFactoryPostProcessor
 * @Description
 * @Author Svamei
 * @Date 14:05 2023/3/7
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}