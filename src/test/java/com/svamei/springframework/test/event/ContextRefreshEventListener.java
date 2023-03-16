package com.svamei.springframework.test.event;

import com.svamei.springframework.context.ApplicationListener;
import com.svamei.springframework.context.event.ContextRefreshedEvent;

/**
 * @ClassName ContextRefreshEventListener
 * @Description
 * @Author Svamei
 * @Date 20:25 2023/3/16
 **/
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Spring 容器初始化完成了，执行事件方法。");
    }
}