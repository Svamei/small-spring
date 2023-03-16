package com.svamei.springframework.context.event;

import com.svamei.springframework.beans.factory.BeanFactory;
import com.svamei.springframework.context.ApplicationEvent;
import com.svamei.springframework.context.ApplicationListener;

/**
 * @ClassName SimpleApplicationEventMulticaster
 * @Description
 * @Author Svamei
 * @Date 19:47 2023/3/16
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}