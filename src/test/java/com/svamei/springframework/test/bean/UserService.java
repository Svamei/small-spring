package com.svamei.springframework.test.bean;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.*;
import com.svamei.springframework.context.ApplicationContext;
import com.svamei.springframework.context.ApplicationContextAware;

/**
 * @ClassName UserService
 * @Description
 * @Author Svamei
 * @Date 9:06 2023/3/1
 **/
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String name;
    private String uId;

    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void queryUserInfo(){
        System.out.println("查询" + name + "信息:" + userDao.queryUserName(uId));
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}