package com.svamei.springframework.beans.factory;

import com.svamei.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName, Object... args) throws BeansException;

    Object getBean(String beanName) throws BeansException;
}
