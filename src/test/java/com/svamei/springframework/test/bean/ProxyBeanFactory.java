package com.svamei.springframework.test.bean;

import com.svamei.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ProxyBeanFactory
 * @Description
 * @Author Svamei
 * @Date 16:47 2023/3/15
 **/
public class ProxyBeanFactory implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
//        InvocationHandler handler = (proxy, method, args) -> {
//
//            Map<String, String> hashMap = new HashMap<>();
//            hashMap.put("10001", "小傅哥");
//            hashMap.put("10002", "八杯水");
//            hashMap.put("10003", "阿毛");
//;
//            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
//        };
//        return (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserDao.class}, handler);

        UserDao userDao = new UserDao();
        userDao.map.put("323", "vvv");
        return userDao;
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}