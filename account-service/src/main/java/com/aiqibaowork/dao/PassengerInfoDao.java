package com.aiqibaowork.dao;

import com.aiqibaowork.entity.PassengerInfo;
import com.aiqibaowork.mapper.PassengerInfoMapper;
import com.aiqibaowork.mapper.PassengerRegisterSourceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 乘客端
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

    public int insertSelective(PassengerInfo passengerInfo){
        return passengerInfoMapper.insertSelective(passengerInfo) ;
    }
    public PassengerInfo selectByParimaryKey(Integer passengerId){
        return passengerInfoMapper.selectByPrimaryKey(passengerId) ;
    }
    public int updateByPrimaryKeySelective(PassengerInfo record) {
        return passengerInfoMapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(PassengerInfo record) {
        return passengerInfoMapper.updateByPrimaryKey(record);
    }

}
