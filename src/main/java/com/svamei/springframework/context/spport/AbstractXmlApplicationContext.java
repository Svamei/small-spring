package com.svamei.springframework.context.spport;

import com.svamei.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.svamei.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @ClassName AbstractXmlApplicationContext
 * @Description
 * @Author Svamei
 * @Date 10:30 2023/3/8
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}