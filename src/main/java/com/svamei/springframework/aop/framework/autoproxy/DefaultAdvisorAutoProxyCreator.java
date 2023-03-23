package com.svamei.springframework.aop.framework.autoproxy;

import com.svamei.springframework.aop.*;
import com.svamei.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.svamei.springframework.aop.framework.ProxyFactory;
import com.svamei.springframework.beans.BeansException;
import com.svamei.springframework.beans.PropertyValues;
import com.svamei.springframework.beans.factory.BeanFactory;
import com.svamei.springframework.beans.factory.BeanFactoryAware;
import com.svamei.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.svamei.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName DefaultAdvisorAutoProxyCreator
 * @Description
 * @Author Svamei
 * @Date 21:16 2023/3/18
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<Object>());

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean, beanName);
    }

    protected Object wrapIfNecessary(Object bean, String name) {

        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();

            if (!classFilter.matches(bean.getClass())) {
                continue;
            }

            AdvisedSupport advised = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);
            advised.setTargetSource(targetSource);
            advised.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advised.setInterceptor((MethodInterceptor) advisor.getAdvice());
            advised.setProxyTargetClass(true);

            // 返回代理对象
            return new ProxyFactory(advised).getProxy();

        }

        return bean;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (!earlyProxyReferences.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }

        return bean;
    }
}