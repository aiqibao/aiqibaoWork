package com.aiqibao.constant;

import lombok.Getter;

/**
 * @Author:aiqibao
 * @Date:2020/10/30 15:34
 * Best wish!
 */
@Getter
public enum  BusinessInterfaceStatus {
    /**
     * 操作成功
     */
    SUCCESS(0,"success"),
    /**
     * 操作失败
     */
    FAIL(1,"fail") ;

    private final int code ;

    private final String value ;

    BusinessInterfaceStatus(int code,String value){
        this.code = code ;
        this.value = value ;
    }
}
