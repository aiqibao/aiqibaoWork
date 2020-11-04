package com.aiqibaowork.dao;

import com.aiqibaowork.entity.PassengerAddress;
import com.aiqibaowork.mapper.PassengerAddressMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/11/2 17:27
 * Best wish!
 */
@Repository
@RequiredArgsConstructor
public class PassengerAddressDao {
    @NonNull
    private PassengerAddressMapper passengerAddressDao;

    public int deleteByPrimaryKey(Integer id) {
        return passengerAddressDao.deleteByPrimaryKey(id);
    }

    public int insert(PassengerAddress record) {
        return passengerAddressDao.insert(record);
    }

    public int insertSelective(PassengerAddress record) {
        return passengerAddressDao.insertSelective(record);
    }

    public PassengerAddress selectByPrimaryKey(Integer id) {
        return passengerAddressDao.selectByPrimaryKey(id);
    }

    public PassengerAddress selectByPassengerInfoId(Integer passengerInfoId) {
        return passengerAddressDao.selectByPassengerInfoId(passengerInfoId);
    }

    public List<PassengerAddress> selectPassengerAddressList(Integer passengerInfoId) {
        return passengerAddressDao.selectPassengerAddressList(passengerInfoId);
    }

    public PassengerAddress selectByAddPassengerInfoId(PassengerAddress passengerInfoId ) {
        return passengerAddressDao.selectByAddPassengerInfoId(passengerInfoId);
    }

    public int updateByPrimaryKeySelective(PassengerAddress record) {
        return passengerAddressDao.updateByPrimaryKeySelective(record);
    }

    public int updatePassengerAddress(PassengerAddress record) {
        return passengerAddressDao.updatePassengerAddress(record);
    }

    public int updateByPrimaryKey(PassengerAddress record) {
        return passengerAddressDao.updateByPrimaryKey(record);
    }
}
