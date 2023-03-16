package com.svamei.springframework.context.event;

import com.svamei.springframework.context.ApplicationEvent;
import com.svamei.springframework.context.ApplicationListener;

/**
 * @ClassName ApplicationEventMulticaster
 * @Description
 * @Author Svamei
 * @Date 16:55 2023/3/16
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}