package com.aiqibaowork.service.impl;

import com.aiqibaowork.dao.PassengerAddressDao;
import com.aiqibaowork.dao.PassengerInfoDao;
import com.aiqibaowork.dao.PassengerWalletDao;
import com.aiqibaowork.entity.PassengerWallet;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.entity.PassengerRegisterSource;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.service.PassengerInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * 乘客端
 * @Author:aiqibao
 * @Date:2020/11/2 15:59
 * Best wish!
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerInfoServiceImp implements PassengerInfoService {

    @NonNull
    private PassengerInfoDao passengerInfoDao ;

    @NonNull
    private PassengerAddressDao passengerAddressDao ;

    @NonNull
    private PassengerWalletDao passengerWalletDao ;

    /**
     * 根据手机号查找客户信息
     *
     * @param phoneNum
     * @return PassengerInfo
     */
    @Override
    public PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum) {
        return passengerInfoDao.queryPassengerInfoByPhoneNum(phoneNum);
    }

    /**
     * 插入乘客信息
     *
     * @param passengerInfo
     */
    @Override
    public void insertPassengerInfo(PassengerInfo passengerInfo) {

    }

    /**
     * 更新乘客的登录时间
     *
     * @param passengerId
     */
    @Override
    public void updatePassengerInfoLoginTime(Integer passengerId) {

    }

    @Override
    public HashMap<String, Object> getPassengerInfoView(GetTokenRequest getTokenRequest) {
        return null;
    }

    @Override
    public ResponseResult updatePassengerInfo(PassengerInfo passengerInfo) {
        return null;
    }

    @Override
    public int initPassengerWallent(Integer passengerId) {
        PassengerWallet passengerWallet = new PassengerWallet() ;
        passengerWallet.setPassengerInfoId(passengerId);
        passengerWallet.setFreezeGiveFee(0d);
        passengerWallet.setFreezeCapital(0d);
        passengerWallet.setGiveFee(0d);
        passengerWallet.setCapital(0d);
        passengerWallet.setCreateTime(new Date());
        return passengerWalletDao.insertSelective(passengerWallet);
    }

    @Override
    public PassengerInfo queryPassengerInfoById(Integer passengerId) {
        return passengerInfoDao.queryPassengerInfoById(passengerId);
    }

    @Override
    public int insertPassengerRegisterSource(PassengerRegisterSource passengerRegisterSource) {
        return 0;
    }

    @Override
    public int updatePassengerInfoById(PassengerInfo passengerInfo) {
        return 0;
    }
}
