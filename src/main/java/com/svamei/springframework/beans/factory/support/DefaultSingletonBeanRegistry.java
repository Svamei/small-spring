package com.svamei.springframework.beans.factory.support;

import com.svamei.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DefaultSingletonBeanRegistry
 * @Description
 * @Author Svamei
 * @Date 10:42 2023/3/1
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonsMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonsMap.get(beanName);
    }

    protected void addSingletone(String beanName, Object bean) {
        singletonsMap.put(beanName, bean);
    }
}