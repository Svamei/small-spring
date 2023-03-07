package com.svamei.springframework.beans.factory.support;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.BeanFactory;
import com.svamei.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName AbstractBeanFactory
 * @Description
 * @Author Svamei
 * @Date 10:59 2023/3/1
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBean(beanName, (Object) null);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        Object singleton = getSingleton(beanName);

        if (singleton != null) {
            return singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}