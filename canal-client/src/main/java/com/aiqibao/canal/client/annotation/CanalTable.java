package com.aiqibao.canal.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 15:09
 * Best wish!
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CanalTable {

    String value() default "";
}
