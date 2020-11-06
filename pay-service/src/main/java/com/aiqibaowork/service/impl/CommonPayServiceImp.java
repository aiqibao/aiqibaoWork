package com.aiqibaowork.service.impl;

import com.aiqibaowork.constant.PayConst;
import com.aiqibaowork.service.CommonPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author:aiqibao
 * @Date:2020/11/6 10:18
 * Best wish!
 */
@Slf4j
@Service
public class CommonPayServiceImp implements CommonPayService {
    @Override
    public String createDescription(Double capital, Double giveFee, String prefix) {
        String deacription = "" ;
        if (capital.compareTo(PayConst.ZERO) > 0 && giveFee.compareTo(PayConst.ZERO) >0){
            deacription = prefix + "(本金+赠额)" ;
        }else if (capital.compareTo(PayConst.ZERO) > 0){
            deacription = prefix + "(本金)" ;
        }else if (giveFee.compareTo(PayConst.ZERO) > 0){
            deacription = prefix + "(赠额)" ;
        }
        return deacription;
    }
}
