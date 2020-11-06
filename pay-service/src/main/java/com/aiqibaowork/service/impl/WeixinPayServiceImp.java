package com.aiqibaowork.service.impl;

import com.aiqibaowork.constant.PayConst;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.request.PayResultRequest;
import com.aiqibaowork.response.WeixinPayResponse;
import com.aiqibaowork.service.CommonPayService;
import com.aiqibaowork.service.WeixinPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:aiqibao
 * @Date:2020/11/5 11:58
 * Best wish!
 */
@Service
@Slf4j
public class WeixinPayServiceImp implements WeixinPayService {
    @Autowired
    private CommonPayService commonPayService ;
    @Override
    public WeixinPayResponse prePay(Integer yid, Double capital, Double giveFee, String source, Integer rechareType, Integer orderId) {
        capital = capital == null ? 0d : capital ;
        giveFee = giveFee == null ? 0d : giveFee ;
        String description = commonPayService.createDescription(capital,giveFee, PayConst.WEIXIN_RECHARGE) ;

        //生成充值记录

        return null;
    }

    @Override
    public Boolean callback(String reqXml) {
        return null;
    }

    @Override
    public ResponseResult payResult(PayResultRequest payResultRequest) {
        return null;
    }
}
