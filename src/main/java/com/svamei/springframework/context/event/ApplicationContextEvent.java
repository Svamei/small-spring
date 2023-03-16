package com.svamei.springframework.context.event;

import com.svamei.springframework.context.ApplicationContext;
import com.svamei.springframework.context.ApplicationEvent;

/**
 * @ClassName ApplicationContextEvent
 * @Description
 * @Author Svamei
 * @Date 16:50 2023/3/16
 **/
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}