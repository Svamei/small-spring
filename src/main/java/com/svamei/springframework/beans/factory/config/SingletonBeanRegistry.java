package com.svamei.springframework.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void destroySingletons();

    void registerSingleton(String beanName, Object singletonObject);
}
