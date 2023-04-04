package com.xiesu.common.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解标识权限范围
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Authc {

    /**
     * 权限范围，默认只能访问自己名下的数据
     */
    AuthcRole authcRole() default AuthcRole.SELF;
}
