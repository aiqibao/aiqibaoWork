package com.aiqibaowork.service;

import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerAddress;

import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/11/2 13:50
 * Best wish!
 */
public interface PassengerAddressService {
    int deleteByPrimaryKey(Integer id);

    int insert(PassengerAddress record);

    int insertSelective(PassengerAddress record);

    PassengerAddress selectByPrimaryKey(Integer id);

    List<PassengerAddress> selectPassengerAddressList(Integer PassengerInfoId);

    PassengerAddress selectByAddPassengerInfoId(PassengerAddress passengerInfoId);

    int updateByPrimaryKeySelective(PassengerAddress record);

    ResponseResult updatePassengerAddress(PassengerAddress record);

    int updateByPrimaryKey(PassengerAddress record);
}
