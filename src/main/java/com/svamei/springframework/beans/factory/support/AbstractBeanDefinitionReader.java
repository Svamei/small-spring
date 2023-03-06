package com.svamei.springframework.beans.factory.support;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.io.DefaultResourceLoader;
import com.svamei.springframework.io.Resource;
import com.svamei.springframework.io.ResourceLoader;

/**
 * @ClassName AbstractBeanDefinitionReader
 * @Description
 * @Author Svamei
 * @Date 19:55 2023/3/6
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}