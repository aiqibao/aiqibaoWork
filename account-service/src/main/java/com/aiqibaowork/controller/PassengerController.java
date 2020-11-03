package com.aiqibaowork.controller;

import com.aiqibaowork.constant.AccountStatusCode;
import com.aiqibaowork.constant.IdentityEnum;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.service.PassengerInfoService;
import com.aiqibaowork.service.PassengerRegistHandleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 * @Author:aiqibao
 * @Date:2020/11/2 11:58
 * Best wish!
 */
@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
@Slf4j
public class PassengerController {

    @NonNull
    private PassengerInfoService passengerInfoService ;

    @NonNull
    private PassengerRegistHandleService passengerRegistHandleService ;

    private static final int NUM = 11 ;

    /**
     * 乘客登录
     * @param request GetTokenRequest 对象
     * @return ResponseResult实例
     */
    @PostMapping(value = "/regist")
    public ResponseResult getVerificationCode(@RequestBody GetTokenRequest request){
        try {
            String phoneNum = request.getPhoneNum() ;
            if (StringUtils.isBlank(phoneNum)){
                return ResponseResult.fail(AccountStatusCode.PHONE_NUM_EMPTY.getCode(),AccountStatusCode.PHONE_NUM_EMPTY.getValue(),phoneNum) ;
            }
            if (NUM!=phoneNum.length()){
                return ResponseResult.fail(AccountStatusCode.PHONE_NUM_DIGIT.getCode(),AccountStatusCode.PHONE_NUM_DIGIT.getValue(),phoneNum);
            }
            if (!Pattern.compile(AccountStatusCode.PHONE_NUMBER_VERIFICATION.getValue()).matcher(phoneNum).matches()){
                return ResponseResult.fail(AccountStatusCode.PHONE_NUM_ERROR.getCode(),AccountStatusCode.PHONE_NUM_ERROR.getValue(),phoneNum) ;
            }
            request.setIdentityStatus(IdentityEnum.PASSENGER.getCode());
            return passengerRegistHandleService.handle(request) ;
        }catch (Exception e){
            log.error("操作异常",e);
            e.printStackTrace();
            return ResponseResult.fail(1,"操作异常",request.getPhoneNum()) ;
        }
    }
}
