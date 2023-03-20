package com.svamei.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author Svamei
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
