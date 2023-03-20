package com.svamei.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.svamei.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @ClassName ClassPathBeanDefinitionScanner
 * @Description
 * @Author Svamei
 * @Date 10:58 2023/3/19
 **/
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition definition : candidates) {
                String scope = resolveBeanScope(definition);
                if (StrUtil.isNotEmpty(scope)) {
                    definition.setScope(scope);
                }
                registry.registerBeanDefinition(determineBeanName(definition), definition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition definition) {
        Class<?> beanClass = definition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);

        if (null != scope) {
            return scope.value();
        }

        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition definition) {
        Class<?> beanClass = definition.getBeanClass();
        Component annotation = beanClass.getAnnotation(Component.class);
        String value = annotation.value();
        if (StrUtil.isNotEmpty(value)) {
            return value;
        }
        return StrUtil.lowerFirst(beanClass.getSimpleName());
    }
}