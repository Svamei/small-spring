package com.svamei.springframework.context;

public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
