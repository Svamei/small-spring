package com.svamei.springframework.context;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.factory.Aware;
import com.svamei.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}