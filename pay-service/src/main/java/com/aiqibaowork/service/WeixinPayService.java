package com.aiqibaowork.service;

import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.request.PayResultRequest;
import com.aiqibaowork.response.WeixinPayResponse;

/**
 * 微信支付
 * @Author:aiqibao
 * @Date:2020/11/5 11:54
 * Best wish!
 */
public interface WeixinPayService {
    WeixinPayResponse prePay(Integer yid,Double capital,Double giveFee,String source,Integer rechareType,Integer orderId) ;
    Boolean callback(String reqXml) ;
    ResponseResult payResult(PayResultRequest payResultRequest) ;
}
