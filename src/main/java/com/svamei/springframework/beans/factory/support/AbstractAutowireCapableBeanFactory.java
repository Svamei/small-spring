package com.svamei.springframework.beans.factory.support;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @ClassName AbstractAutowireCapableBeanFactory
 * @Description
 * @Author Svamei
 * @Date 11:13 2023/3/1
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {

        Object bean = createBeanInstance(beanDefinition, beanName, args);
        addSingletone(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = beanClass.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            if (null != args && constructor.getParameterCount() == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }
}