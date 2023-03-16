package com.svamei.springframework.context;

import java.util.EventObject;

/**
 * @ClassName ApplicationEvent
 * @Description
 * @Author Svamei
 * @Date 16:49 2023/3/16
 **/
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}