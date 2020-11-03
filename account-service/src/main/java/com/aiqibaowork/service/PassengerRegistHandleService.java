package com.aiqibaowork.service;

import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.request.GetTokenRequest;

/**
 * @Author:aiqibao
 * @Date:2020/10/30 14:53
 * Best wish!
 */
public interface PassengerRegistHandleService {

    /**
     * 乘客登入
     * @param getTokenRequest
     * @return ResponseResult
     */
    ResponseResult handle(GetTokenRequest getTokenRequest) ;

    /**
     * 乘客登出
     * @param request
     * @return ResponseResult
     * @throws Exception
     */
    ResponseResult checout(GetTokenRequest request) throws Exception ;
}
