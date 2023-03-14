package com.svamei.springframework.context.spport;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.config.BeanPostProcessor;
import com.svamei.springframework.context.ApplicationContext;
import com.svamei.springframework.context.ApplicationContextAware;

/**
 * @ClassName ApplicationContextAwareProcessor
 * @Description
 * @Author Svamei
 * @Date 15:18 2023/3/14
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}