package com.svamei.springframework.test.bean;

import com.svamei.springframework.beans.factory.DisposableBean;
import com.svamei.springframework.beans.factory.InitializingBean;

/**
 * @ClassName UserService
 * @Description
 * @Author Svamei
 * @Date 9:06 2023/3/1
 **/
public class UserService implements InitializingBean, DisposableBean {

    private String name;
    private String uId;

    private UserDao userDao;

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
}