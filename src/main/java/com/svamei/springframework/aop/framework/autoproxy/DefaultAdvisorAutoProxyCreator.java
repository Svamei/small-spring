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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * @ClassName DefaultAdvisorAutoProxyCreator
 * @Description
 * @Author Svamei
 * @Date 21:16 2023/3/18
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            AdvisedSupport advised = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

            advised.setTargetSource(targetSource);
            advised.setInterceptor((MethodInterceptor) advisor.getAdvice());
            advised.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advised.setProxyTargetClass(true);

            return new ProxyFactory(advised).getProxy();
        }
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
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
        return bean;
    }
}