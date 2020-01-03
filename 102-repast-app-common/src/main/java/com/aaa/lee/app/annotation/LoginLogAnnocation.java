package com.aaa.lee.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  自定义注解
 *       @Target():标识了自定义所需要使用的范围(所需要使用的地方)
 *       @Retention():标识了自定义生效的时间
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLogAnnocation {

    /**
     * 要执行的操作类型：例 delete操作  login操作
     * @return
     */
    String operationType() default "";


    /**
     * 要执行的具体操作：例 删除用户  添加用户
     * @return
     */
    String operationName() default "";




}
