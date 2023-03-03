package com.svamei.springframework.test.bean;

/**
 * @ClassName UserService
 * @Description
 * @Author Svamei
 * @Date 9:06 2023/3/1
 **/
public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}