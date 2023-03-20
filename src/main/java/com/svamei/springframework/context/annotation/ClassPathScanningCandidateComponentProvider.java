package com.svamei.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.svamei.springframework.beans.factory.config.BeanDefinition;
import com.svamei.springframework.stereotype.Component;
import com.svamei.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @ClassName ClassPathScanningCandidateComponentProvider
 * @Description
 * @Author Svamei
 * @Date 10:53 2023/3/19
 **/
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        LinkedHashSet<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}