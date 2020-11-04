package com.aiqibaowork.controller;

import com.aiqibaowork.constant.AccountStatusCode;
import com.aiqibaowork.constant.BusinessInterfaceStatus;
import com.aiqibaowork.constant.IdentityEnum;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.request.PassengerInfoExtRequest;
import com.aiqibaowork.request.PassengerInfoRequest;
import com.aiqibaowork.service.PassengerAddressService;
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

    @NonNull
    private PassengerAddressService passengerAddressService ;

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

    /**
     * 查询乘客详情
     * @param request
     * @return
     */
    @PostMapping(value = "/passengerInfo")
    public ResponseResult getPassengerInfo(@RequestBody GetTokenRequest request){
        return ResponseResult.success(passengerInfoService.getPassengerInfoView(request)) ;
    }

    /**
     * 修改乘客信息
     * @param request
     * @return
     */
    @PostMapping(value = "/updatePassengerInfo")
    public ResponseResult updatePassengerInfo(@RequestBody PassengerInfoRequest request){
        ResponseResult responseResult ;
        if (null != request.getId()){
            if (null != request.getData()){
                if (null != request.getData().getPassengerAddress()){
                    request.getData().getPassengerAddress().setPassengerInfoId(request.getId()) ;
                }
                if (null != request.getData().getPassengerInfo()){
                    request.getData().getPassengerInfo().setId(request.getId());
                }
            }
        }
        if (null != request.getData()){
            if (null != request.getData().getPassengerAddress()){
                responseResult = passengerAddressService.updatePassengerAddress(request.getData().getPassengerAddress());
                if (BusinessInterfaceStatus.SUCCESS.getCode() != responseResult.getCode()){
                    return responseResult ;
                }
            }
            if (null != request.getData().getPassengerInfo()){
                responseResult = passengerInfoService.updatePassengerInfo(request.getData().getPassengerInfo());
                if (BusinessInterfaceStatus.SUCCESS.getCode() != responseResult.getCode()){
                    return responseResult ;
                }
            }
        }
        log.info("修改乘客信息成功");
        return ResponseResult.success("") ;
    }
    @PostMapping(value = "/ext")
    public ResponseResult updatePassengerInfoExt(@RequestBody PassengerInfoExtRequest passengerInfoExtRequest){
        Integer id = passengerInfoExtRequest.getId() ;
        if (null == id){
            return ResponseResult.fail(BusinessInterfaceStatus.FAIL.getCode(),"乘客id为空") ;
        }
        Integer isContact = passengerInfoExtRequest.getIsContact() ;
        Integer isShare = passengerInfoExtRequest.getIsShare() ;
        String sharingTime = passengerInfoExtRequest.getSharingTime() ;
        if (null == isContact && null == isShare && StringUtils.isBlank(sharingTime)){
            return ResponseResult.fail(BusinessInterfaceStatus.FAIL.getCode(),"乘客额外信息为空") ;
        }
        PassengerInfo passengerInfo = new PassengerInfo() ;
        passengerInfo.setId(id);
        if (null != isContact){
            passengerInfo.setIsContact(isContact);
        }
        if (null != isShare){
            passengerInfo.setIsShare(isShare);
        }
        if (!StringUtils.isBlank(sharingTime)){
            passengerInfo.setSharingTime(sharingTime);
        }
        int row = passengerInfoService.updatePassengerInfoById(passengerInfo) ;
        if (row>0){
            return ResponseResult.success("") ;
        }else{
            return ResponseResult.fail("无可更新的乘客额外信息") ;

        }
    }
}
