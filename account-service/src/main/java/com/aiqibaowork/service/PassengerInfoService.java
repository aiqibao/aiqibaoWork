package com.aiqibaowork.service;

import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.entity.PassengerRegisterSource;
import com.aiqibaowork.request.GetTokenRequest;

import java.util.HashMap;

/**
 * @Author:aiqibao
 * @Date:2020/11/2 13:49
 * Best wish!
 */
public interface PassengerInfoService {
    /**
     * 根据手机号查找客户信息
     * @param phoneNum
     * @return PassengerInfo
     */
    PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum) ;

    /**
     * 插入乘客信息
     * @param passengerInfo
     */
    void insertPassengerInfo(PassengerInfo passengerInfo) ;

    /**
     * 更新乘客的登录时间
     * @param passengerId
     */
    void updatePassengerInfoLoginTime(Integer passengerId) ;


    HashMap<String,Object> getPassengerInfoView(GetTokenRequest getTokenRequest) ;

    ResponseResult updatePassengerInfo(PassengerInfo passengerInfo) ;

    int initPassengerWallent(Integer passengerId) ;

    PassengerInfo queryPassengerInfoById(Integer passengerId) ;

    int insertPassengerRegisterSource(PassengerRegisterSource passengerRegisterSource) ;

    int updatePassengerInfoById(PassengerInfo passengerInfo) ;
}
