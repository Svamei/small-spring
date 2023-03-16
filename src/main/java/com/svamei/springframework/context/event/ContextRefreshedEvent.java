package com.svamei.springframework.context.event;

/**
 * @ClassName ContextRefreshedEvent
 * @Description
 * @Author Svamei
 * @Date 16:53 2023/3/16
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}