package com.svamei.springframework.test.event;

import com.svamei.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @ClassName CustomEventListener
 * @Description
 * @Author Svamei
 * @Date 20:13 2023/3/16
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}