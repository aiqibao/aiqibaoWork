package com.aiqibao.constant;

/**
 * 通用枚举接口
 * @Author:aiqibao
 * @Date:2020/10/30 16:12
 * Best wish!
 */
public interface CodeEnum {
    /**
     * 返回枚举值的code值
     * @return int类型的code值
     */
    int getCode() ;

    /**
     * 返回枚举值的code值
     * @return 字符串格式的code值
     */
    default String getCodeAsString(){
        return "" + getCode() ;
    }
}
