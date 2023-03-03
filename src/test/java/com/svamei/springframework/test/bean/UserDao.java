package com.svamei.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserDao
 * @Description
 * @Author Svamei
 * @Date 11:03 2023/3/3
 **/
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "aaa");
        map.put("10002", "bbb");
        map.put("10003", "ccc");
    }

    public String queryUserName(String uId) {
        return map.get(uId);
    }
}