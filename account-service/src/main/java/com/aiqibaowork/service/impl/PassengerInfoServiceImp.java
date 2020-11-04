package com.aiqibaowork.service.impl;

import com.aiqibaowork.constant.BusinessInterfaceStatus;
import com.aiqibaowork.dao.PassengerAddressDao;
import com.aiqibaowork.dao.PassengerInfoDao;
import com.aiqibaowork.dao.PassengerWalletDao;
import com.aiqibaowork.entity.PassengerAddress;
import com.aiqibaowork.entity.PassengerWallet;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.entity.PassengerRegisterSource;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.service.PassengerInfoService;
import com.aiqibaowork.util.EncriptUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
        passengerInfoDao.insertSelective(passengerInfo) ;
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
        HashMap<String,Object> view = new HashMap<>(16) ;
        PassengerInfo passengerInfo = passengerInfoDao.selectByParimaryKey(getTokenRequest.getId()) ;
        log.info("获取乘客信息为：{}",passengerInfo);
        PassengerAddress passengerAddress = new PassengerAddress() ;
        passengerAddress.setPassengerInfoId(passengerInfo.getId());
        List<PassengerAddress> passengerAddressList = new ArrayList<>() ;
        if (null != getTokenRequest.getType()){
            passengerAddress.setType(getTokenRequest.getType());
            passengerAddress = passengerAddressDao.selectByAddPassengerInfoId(passengerAddress) ;
        }else{
            passengerAddressList = passengerAddressDao.selectPassengerAddressList(getTokenRequest.getId()) ;
        }
        if (null != passengerInfo){
            String decrypt ;
            if (!StringUtils.isEmpty(passengerInfo.getPhone())){
                decrypt = EncriptUtil.decrypt(passengerInfo.getPhone()) ;
                passengerInfo.setPhone(decrypt);
            }
            view.put("passengerInfo",passengerInfo) ;
        }
        if (null != passengerAddressList && 0 != passengerAddressList.size()){
            view.put("passengerInfoList",passengerAddressList) ;
        }
        if (null != passengerAddress && null != getTokenRequest.getType()){
            view.put("passengerAddress" ,passengerAddress) ;
        }
        return view;
    }

    @Override
    public ResponseResult updatePassengerInfo(PassengerInfo passengerInfo) {
        if (null != passengerInfo){
            if (!StringUtils.isEmpty(passengerInfo.getPhone())){
                String decrypt = EncriptUtil.encryptionPhoneNumer(passengerInfo.getPhone()) ;
                passengerInfo.setPhone(decrypt);
            }
        }
        int updateOrInser ;
        if (null != passengerInfo && null != passengerInfo.getId()){
            updateOrInser = passengerInfoDao.updateByPrimaryKeySelective(passengerInfo) ;
        }else{
            if (null != passengerInfo){
                passengerInfo.setCreateTime(new Date());
            }
            updateOrInser = passengerInfoDao.insertSelective(passengerInfo) ;
        }
        if (0 == updateOrInser){
            return ResponseResult.fail(BusinessInterfaceStatus.FAIL.getCode(),"修改或添加乘客信息失败！");
        }else{
            return ResponseResult.success("" );
        }
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
        return passengerInfoDao.updateByPrimaryKeySelective(passengerInfo);
    }
}
