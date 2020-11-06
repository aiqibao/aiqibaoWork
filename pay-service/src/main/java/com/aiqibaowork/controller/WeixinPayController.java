package com.aiqibaowork.controller;

import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.request.PayRequest;
import com.aiqibaowork.response.WeixinPayResponse;
import com.aiqibaowork.service.WeixinPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付
 * @Author:aiqibao
 * @Date:2020/11/5 11:50
 * Best wish!
 */
@RestController
@RequestMapping("/weixinPay")
@Slf4j
public class WeixinPayController {
    @Autowired
    private WeixinPayService weixinPayService ;

    public ResponseResult pretreatment(@RequestBody PayRequest payRequest){
        Integer yid = payRequest.getYid() ;
        Double capital = payRequest.getCapital() ;
        Double giveFee = payRequest.getGiveFee() ;
        String source = payRequest.getSource() ;
        Integer rechargeType = payRequest.getRechargeType() ;
        Integer orderId = payRequest.getOrderId() ;
        WeixinPayResponse response = weixinPayService.prePay(yid,capital,giveFee,source,rechargeType,orderId) ;
        return ResponseResult.success(response) ;
    }
}
