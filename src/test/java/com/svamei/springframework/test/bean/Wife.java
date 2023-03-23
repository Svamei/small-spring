package com.svamei.springframework.test.bean;

import com.svamei.springframework.beans.factory.annotation.Autowired;
import com.svamei.springframework.stereotype.Component;

/**
 * @ClassName Wife
 * @Description
 * @Author Svamei
 * @Date 17:56 2023/3/23
 **/

@Component
public class Wife {

    @Autowired
    private Husband husband;

    public String queryHusband() {
        return husband.queryWife();
    }

}