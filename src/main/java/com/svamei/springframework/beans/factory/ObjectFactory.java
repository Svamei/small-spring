package com.svamei.springframework.beans.factory;

import com.svamei.springframework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
