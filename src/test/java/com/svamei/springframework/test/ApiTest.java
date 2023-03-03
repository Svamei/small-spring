package com.svamei.springframework.test;

import com.svamei.springframework.beans.factory.BeanFactory;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.svamei.springframework.beans.factory.support.SimpleInstantiationStrategy;
import com.svamei.springframework.test.bean.UserService;

import org.junit.Test;

/**
 * @ClassName ApiTest
 * @Description
 * @Author Svamei
 * @Date 9:07 2023/3/1
 **/
public class ApiTest {

    @Test
    public void test_BeanFactory() {

        // 初始化 BeanFactory与BeanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class);

        // 注册Bean
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        //实例化策略使用JDK反射
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "Svamei");
        //UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
        userService.queryUserInfo();

        // 获取Bean
        UserService userService2 = (UserService) beanFactory.getBean("userService");
        System.out.println(userService2);
    }
}