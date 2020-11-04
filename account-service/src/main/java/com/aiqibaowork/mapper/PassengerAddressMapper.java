package com.aiqibaowork.mapper;

import com.aiqibaowork.entity.PassengerAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PassengerAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PassengerAddress record);

    int insertSelective(PassengerAddress record);

    PassengerAddress selectByPrimaryKey(Integer id);

    PassengerAddress selectByPassengerInfoId(Integer passengerInfoId);

    List<PassengerAddress> selectPassengerAddressList(Integer PassengerInfoId);

    PassengerAddress selectByAddPassengerInfoId(PassengerAddress passengerInfoId);

    int updateByPrimaryKeySelective(PassengerAddress record);

    int updatePassengerAddress(PassengerAddress record);

    int updateByPrimaryKey(PassengerAddress record);
}