package com.svamei.springframework.context.spport;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.svamei.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @ClassName AbstarctRefreshApplicationContext
 * @Description
 * @Author Svamei
 * @Date 10:08 2023/3/8
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}