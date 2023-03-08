package com.svamei.springframework.test.common;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.PropertyValue;
import com.svamei.springframework.beans.PropertyValues;
import com.svamei.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @ClassName MyBeanFactoryPostProcessor
 * @Description
 * @Author Svamei
 * @Date 12:23 2023/3/8
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userServiceBeanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userServiceBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "kxs"));
    }
}