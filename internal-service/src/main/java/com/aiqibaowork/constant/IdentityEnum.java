package com.aiqibaowork.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 身份类型
 * @Author:aiqibao
 * @Date:2020/11/3 14:17
 * Best wish!
 */
@Getter
@AllArgsConstructor
public enum IdentityEnum {
    /**
     * 乘客
     */
    PASSENGER(1,"乘客"),
    /**
     * 司机
     */
    DRIVER(2,"司机"),
    /**
     * 车机
     */
    CAR_SCREEN(3,"车机"),
    /**
     * 大屏
     */
    LARGE_SCREEN(4,"大屏"),
    /**
     * 其他
     */
    OTHER(9,"其他");


    private int code ;
    private String value ;


}
