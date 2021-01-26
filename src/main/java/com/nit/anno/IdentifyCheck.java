package com.nit.anno;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentifyCheck {
    String value() default "";

}
