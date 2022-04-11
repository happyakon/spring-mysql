package com.akon.spring.mysql.annotation;

import java.lang.annotation.*;

/**
 * Controller日志记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLogs {

    String description() default "";
}
