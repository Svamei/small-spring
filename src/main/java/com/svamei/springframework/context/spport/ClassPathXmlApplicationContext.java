package com.svamei.springframework.context.spport;

import com.svamei.springframework.beans.BeansException;

/**
 * @ClassName ClassPathXmlApplicationContext
 * @Description
 * @Author Svamei
 * @Date 10:36 2023/3/8
 **/
public class ClassPathXmlApplicationContext extends  AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}