package com.aiqibao.dto;

import com.aiqibao.constant.BusinessInterfaceStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 通用返回值处理类
 * @Author:aiqibao
 * @Date:2020/10/30 14:55
 * Best wish!
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("unchecked")
public class ResponseResult<T> implements Serializable {
    private int code ;
    private String message ;
    private T data ;

    /**
     * 返回成功数据（status：200）
     * @param data 数据内容
     * @param <T> 数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(BusinessInterfaceStatus.SUCCESS.getCode())
                .setMessage(BusinessInterfaceStatus.SUCCESS.getValue())
                .setData(data);
    }

    /**
     * 返回成功数据（status：200）
     * @return ResponseResult实例
     */
    public static ResponseResult success(){
        return success(null) ;
    }

    /**
     * 返回失败数据（status：500）
     * @param data  数据内容
     * @param <T>   返回类型
     * @return      ResponseResult实例
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name()).setData(data) ;
    }

    /**
     * 返回自定义错误
     * @param code  自定义错误码
     * @param value 自定义错误
     * @return      ResponseResult实例
     */
    public static ResponseResult fail(int code,String value){
        return new ResponseResult().setCode(code).setMessage(value);
    }

    /**
     *
     * @param code  自定义错误码
     * @param value 自定义错误
     * @param data  自定义数据
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code,String value,String data){
        return new ResponseResult().setCode(code).setMessage(value).setData(data) ;
    }

}
