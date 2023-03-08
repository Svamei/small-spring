package com.svamei.springframework.test.common;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.config.BeanPostProcessor;
import com.svamei.springframework.test.bean.UserService;

/**
 * @ClassName MyBeanPostProcessor
 * @Description
 * @Author Svamei
 * @Date 12:26 2023/3/8
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setuId("10003");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}