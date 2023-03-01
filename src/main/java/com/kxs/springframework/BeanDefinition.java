package com.kxs.springframework;

/**
 * @ClassName BeanDefinition
 * @Description
 * @Author Svamei
 * @Date 8:55 2023/3/1
 **/
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}