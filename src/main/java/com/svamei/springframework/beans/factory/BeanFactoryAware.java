package com.svamei.springframework.beans.factory;

import com.svamei.springframework.beans.BeansException;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
