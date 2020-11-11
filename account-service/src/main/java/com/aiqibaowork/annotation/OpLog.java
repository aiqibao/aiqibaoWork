package com.aiqibaowork.annotation;

import com.aiqibaowork.constant.OpType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:aiqibao
 * @Date:2020/11/9 14:16
 * Best wish!
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
    /**
     * 业务类型，如新增、删除、修改
     * @return OpType
     */
    public OpType opType() ;

    /**
     * 业务对象名称，如订单、库存、价格
     * @return
     */
    public String opItem() ;

    /**
     * 业务对象编号表达式，描述了如何获取订单号的表达式
     * @return
     */
    public String opItemIdExpression() ;
}
