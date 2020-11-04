package com.aiqibaowork.service.impl;

import com.aiqibaowork.constant.IdentityEnum;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.entity.PassengerRegisterSource;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.response.PassengerRegistResponse;
import com.aiqibaowork.service.AuthService;
import com.aiqibaowork.service.PassengerInfoService;
import com.aiqibaowork.service.PassengerRegistHandleService;
import com.aiqibaowork.util.EncriptUtil;
import com.aiqibaowork.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:aiqibao
 * @Date:2020/10/30 15:58
 * Best wish!
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerRegistHandleServiceImp implements PassengerRegistHandleService {
    @NonNull
    private AuthService authService;
    @NonNull
    private PassengerInfoService passengerInfoService ;


    /**
     * 乘客登入
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    public ResponseResult handle(GetTokenRequest request) {
        String phoneNum = request.getPhoneNum() ;
        log.info("乘客的手机号为:{}",phoneNum);
        //查询乘客信息
        String strPhoneNum = EncriptUtil.toHexString(EncriptUtil.encrypt(phoneNum)).toUpperCase() ;
        log.info("加密后手机号：{}",strPhoneNum);
        PassengerInfo passengerInfo = passengerInfoService.queryPassengerInfoByPhoneNum(strPhoneNum) ;
        log.info("根据手机号查询,乘客信息{}",passengerInfo);
        int isNew = 0 ;
        int passengerId;
        //如果没有,则创建
        if (null == passengerInfo){
            Date date = new Date() ;
            PassengerInfo passengerInfoTmp = new PassengerInfo() ;
            isNew = 1 ;
            passengerInfoTmp.setLastLoginTime(date);
            //登录方式 1.验证码
            Byte method = 1 ;
            passengerInfoTmp.setLastLoginMethod(method);
            passengerInfoTmp.setPhone(strPhoneNum);
            passengerInfoTmp.setRegisterTime(date);
            passengerInfoTmp.setBalance(BigDecimal.ZERO);
            passengerInfoTmp.setCreateTime(date);
            log.info("新增客户手机号：{}",passengerInfoTmp.getPhone());
            passengerInfoService.insertPassengerInfo(passengerInfoTmp);
            passengerId = passengerInfoTmp.getId() ;

            //新增注册来源
            try{
                PassengerRegisterSource passengerRegisterSource = new PassengerRegisterSource() ;
                passengerRegisterSource.setPassengerInfoId(passengerId);
                String registerSource = request.getRegisterSource() ;
                passengerRegisterSource.setRegisterSource(registerSource);
                passengerRegisterSource.setMarketChannel(request.getMarketChannel());
                passengerRegisterSource.setCreateTime(new Date());
                passengerInfoService.insertPassengerRegisterSource(passengerRegisterSource) ;
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("乘客注册或登录 - {} - 检验注册状态 - 用户未注册，已插入新用户记录表",phoneNum);
            passengerInfoService.initPassengerWallent(passengerId) ;
        }else{
            log.info("乘客注册或登录 - {} - 检验注册状态 - 用户已注册") ;
            passengerId = passengerInfo.getId() ;
            passengerInfoService.updatePassengerInfoLoginTime(passengerId);
        }
        //乘客登录,生成jwtStr
        String subject = IdentityEnum.PASSENGER.getCode()+"_"+phoneNum+"_"+passengerId ;
        log.info("token:{}",subject);
        String jwtStr = authService.createToken(subject) ;
        log.info("乘客注册或登录用户 - {} - access_token：{}",phoneNum,jwtStr);
        passengerInfo = passengerInfoService.queryPassengerInfoByPhoneNum(strPhoneNum) ;
        long dateTime = passengerInfo.getBirthday()==null?0:passengerInfo.getBirthday().getTime();
        return createResponse(jwtStr,passengerInfo.getPassengerName(),passengerInfo.getGender(),passengerInfo.getBalance()
        ,passengerInfo.getPhone(),passengerInfo.getHeadImg(),passengerInfo.getId(),passengerInfo.getLastLoginTime(),passengerInfo.getLastLoginMethod()
        ,passengerInfo.getIsContact(),passengerInfo.getIsShare(),passengerInfo.getSharingTime(),dateTime,isNew);
    }

    /**
     * 封装响应协议
     * @param accessToken   token
     * @param passengerName  用户姓名
     * @param sex            性别
     * @param balance        账户
     * @param phoneNum       加密后的手机号
     * @param headImg       头像
     * @param id             用户id
     * @param lastLoginTime 登陆时间
     * @param method       登录方式
     * @param isContact
     * @param isShare
     * @param sharingTime
     * @param birthday
     * @param isNewer
     * @return ResponseResult 实例
     */
    private ResponseResult createResponse(String accessToken,String passengerName,Byte sex,BigDecimal balance
                                          ,String phoneNum,String headImg,Integer id,Date lastLoginTime,Byte method,Integer isContact
                                          ,Integer isShare,String sharingTime,long birthday,Integer isNewer) {
        PassengerRegistResponse response = new PassengerRegistResponse() ;
        response.setStatus("0");
        response.setAccessToken(accessToken);
        response.setPassengerName(StringUtils.isBlank(passengerName)?"":passengerName);
        response.setGender(sex==null?0:sex);
        response.setId(id);
        response.setBalance(balance);
        response.setPhoneNum(phoneNum);
        response.setHeadImg(headImg);
        String jPushId = IdentityEnum.PASSENGER.getCode() + "_" + phoneNum + "_" + Calendar.getInstance().getTimeInMillis() ;
        response.setJpushId(jPushId);
        response.setLastLoginTime(lastLoginTime);
        response.setLastLoginMethod(method);
        response.setIsContact(isContact);
        response.setIsShare(isShare);
        response.setSharingTime(sharingTime);
        response.setBirthday(birthday);
        response.setIsNewer(isNewer);
        return ResponseResult.success(response) ;

    }

    /**
     * 乘客登出
     *
     * @param request
     * @return ResponseResult
     * @throws Exception
     */
    @Override
    public ResponseResult checout(GetTokenRequest request) throws Exception {
        String strToken = request.getToken() ;
        Claims claims = JwtUtil.parseJwt(strToken) ;
        String subject = claims.getSubject() ;
        authService.deleteToken(subject);
        return ResponseResult.success();
    }
}
