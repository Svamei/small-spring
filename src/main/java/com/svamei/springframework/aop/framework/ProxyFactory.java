package com.svamei.springframework.aop.framework;

import com.svamei.springframework.aop.AdvisedSupport;

/**
 * @ClassName ProxyFactory
 * @Description
 * @Author Svamei
 * @Date 20:42 2023/3/18
 **/
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}