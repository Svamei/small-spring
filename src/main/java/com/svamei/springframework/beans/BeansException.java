package com.svamei.springframework.beans;

/**
 * @ClassName BeansEcception
 * @Description
 * @Author Svamei
 * @Date 10:56 2023/3/1
 **/
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}