package com.svamei.springframework.context;

import com.svamei.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
