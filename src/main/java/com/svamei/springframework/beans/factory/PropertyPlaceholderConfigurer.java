package com.svamei.springframework.beans.factory;

import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.PropertyValue;
import com.svamei.springframework.beans.PropertyValues;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.svamei.springframework.io.DefaultResourceLoader;
import com.svamei.springframework.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropertyPlaceholderConfigurer
 * @Description
 * @Author Svamei
 * @Date 10:29 2023/3/19
 **/
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    
                    String strValue = (String) value;
                    StringBuilder sb = new StringBuilder(strValue);
                    int startIdx = strValue.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strValue.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strValue.substring(startIdx + 2, stopIdx);
                        String propVal = properties.getProperty(propKey);
                        sb.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), sb.toString()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}