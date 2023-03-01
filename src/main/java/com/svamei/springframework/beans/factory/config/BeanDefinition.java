package com.svamei.springframework.beans.factory.config;

/**
 * @ClassName BeanDefinition
 * @Description
 * @Author Svamei
 * @Date 10:35 2023/3/1
 **/
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}