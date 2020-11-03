package com.aiqibaowork.dao;

import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.mapper.PassengerInfoMapper;
import com.aiqibaowork.mapper.PassengerRegisterSourceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @Author:aiqibao
 * @Date:2020/11/2 17:23
 * Best wish!
 */
@Repository
@RequiredArgsConstructor
public class PassengerInfoDao {
    @NonNull
    private PassengerInfoMapper passengerInfoMapper ;

    @NonNull
    private PassengerRegisterSourceMapper passengerRegisterSourceMapper ;

    public PassengerInfo queryPassengerInfoById(Integer passengerInfoId){
        return passengerInfoMapper.selectByPrimaryKey(passengerInfoId) ;
    }

    public PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum){
        return passengerInfoMapper.queryPassengerInfoByPhoneNum(phoneNum) ;
    }
}
