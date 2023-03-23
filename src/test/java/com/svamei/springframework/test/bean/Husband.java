package com.svamei.springframework.test.bean;

import com.svamei.springframework.beans.factory.annotation.Autowired;
import com.svamei.springframework.stereotype.Component;

/**
 * @ClassName Husband
 * @Description
 * @Author Svamei
 * @Date 17:55 2023/3/23
 **/

@Component
public class Husband {

    @Autowired
    private Wife wife;

    public String queryWife() {
        return "Husband.wife";
    }
}