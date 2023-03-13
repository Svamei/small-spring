package com.svamei.springframework.beans.factory;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
