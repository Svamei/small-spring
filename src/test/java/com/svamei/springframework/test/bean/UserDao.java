package com.svamei.springframework.test.bean;

import com.svamei.springframework.context.annotation.Scope;
import com.svamei.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserDao
 * @Description
 * @Author Svamei
 * @Date 11:03 2023/3/3
 **/

@Component
@Scope("property")
public class UserDao {

    public static Map<String, String> map = new HashMap<>();

    public void initData() {
        map.put("10001", "aaa");
        map.put("10002", "bbb");
        map.put("10003", "ccc");
    }

    public void destroyData(){
        System.out.println("执行：destroy-method");
        map.clear();
    }

    public String queryUserName(String uId) {
        return "cha dao le";
    }
}