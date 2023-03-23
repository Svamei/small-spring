package com.svamei.springframework.beans.factory.support;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.DisposableBean;
import com.svamei.springframework.beans.factory.ObjectFactory;
import com.svamei.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DefaultSingletonBeanRegistry
 * @Description
 * @Author Svamei
 * @Date 10:42 2023/3/1
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonsMap = new ConcurrentHashMap<>();

    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    protected static final Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        Object singleton = singletonsMap.get(beanName);
        if (singleton == null) {
            singleton = earlySingletonObjects.get(beanName);
            if (singleton == null) {
                ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
                if (objectFactory != null) {
                    singleton = objectFactory.getObject();
                    earlySingletonObjects.put(beanName, singleton);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singleton;
    }

    protected void addSingleton(String beanName, Object bean) {
        singletonsMap.put(beanName, bean);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        addSingleton(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonsMap.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }
}