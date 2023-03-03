package com.svamei.springframework.beans;

/**
 * @ClassName PropertyValue
 * @Description
 * @Author Svamei
 * @Date 10:28 2023/3/3
 **/
public class PropertyValue {

    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}