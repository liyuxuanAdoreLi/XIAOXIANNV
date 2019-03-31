package com.example.admin.woailiushuang.consts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc
 * @auth ${user}
 * @time 2019/3/26 11:51
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscripe {
    ThMode threadMode() default ThMode.MAIN;

}
