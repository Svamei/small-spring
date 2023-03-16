package com.svamei.springframework.context.event;

/**
 * @ClassName ContextClosedEvent
 * @Description
 * @Author Svamei
 * @Date 16:52 2023/3/16
 **/
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}