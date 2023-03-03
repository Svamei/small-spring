package com.svamei.springframework.beans.factory.config;

/**
 * @ClassName BeanReference
 * @Description
 * @Author Svamei
 * @Date 10:47 2023/3/3
 **/
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}