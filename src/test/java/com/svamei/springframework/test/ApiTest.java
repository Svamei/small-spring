package com.svamei.springframework.test;

import com.svamei.springframework.beans.PropertyValue;
import com.svamei.springframework.beans.PropertyValues;
import com.svamei.springframework.beans.factory.BeanFactory;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.config.BeanReference;
import com.svamei.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.svamei.springframework.beans.factory.support.SimpleInstantiationStrategy;
import com.svamei.springframework.context.spport.ClassPathXmlApplicationContext;
import com.svamei.springframework.test.bean.UserDao;
import com.svamei.springframework.test.bean.UserService;

import org.junit.Test;

/**
 * @ClassName ApiTest
 * @Description
 * @Author Svamei
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

    //测试属性注入
    @Test
    public void test_BeanFactoryWithDI() {
        // 初始化 BeanFactory与BeanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        // UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);

        // 注册Bean
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        //实例化策略使用JDK反射
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "Svamei");
        //UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
        userService.queryUserInfo();
    }

    @Test
    public void testXmlApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}