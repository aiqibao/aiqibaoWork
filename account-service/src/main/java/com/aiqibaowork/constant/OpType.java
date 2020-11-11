package com.aiqibaowork.constant;

/**
 * @Author:aiqibao
 * @Date:2020/11/9 14:39
 * Best wish!
 */
public enum OpType {
    QUERY(1,"删除"),
    UPDATE(2,"修改"),
    DELETE(3,"删除");
    int code ;
    String value ;

    OpType(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
