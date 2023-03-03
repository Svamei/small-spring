package com.svamei.springframework.test.bean;

/**
 * @ClassName UserService
 * @Description
 * @Author Svamei
 * @Date 9:06 2023/3/1
 **/
public class UserService {

    private String name;
    private String uId;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
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
}