package com.aiqibaowork.service.impl;

import com.aiqibaowork.constant.BusinessInterfaceStatus;
import com.aiqibaowork.dao.PassengerAddressDao;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.entity.PassengerAddress;
import com.aiqibaowork.service.PassengerAddressService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/11/4 15:45
 * Best wish!
 */
@Service
@RequiredArgsConstructor
public class PassengerAddressServiceImp implements PassengerAddressService {

    @NonNull
    private PassengerAddressDao passengerAddressDao ;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(PassengerAddress record) {
        return 0;
    }

    @Override
    public int insertSelective(PassengerAddress record) {
        return 0;
    }

    @Override
    public PassengerAddress selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<PassengerAddress> selectPassengerAddressList(Integer PassengerInfoId) {
        return null;
    }

    @Override
    public PassengerAddress selectByAddPassengerInfoId(PassengerAddress passengerInfoId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PassengerAddress record) {
        return 0;
    }

    @Override
    public ResponseResult updatePassengerAddress(PassengerAddress record) {
        int updateOrInsert ;
        if (null != record.getPassengerInfoId()){
            PassengerAddress passengerAddressTemp = passengerAddressDao.selectByAddPassengerInfoId(record) ;
            if (null == passengerAddressTemp){
                record.setCreateTime(new Date());
                updateOrInsert = passengerAddressDao.insertSelective(record) ;
            }else {
                updateOrInsert = passengerAddressDao.updateByPrimaryKeySelective(record) ;
            }
        }else{
            record.setCreateTime(new Date());
            updateOrInsert = passengerAddressDao.insertSelective(record) ;
        }
        if (0 == updateOrInsert){
            return ResponseResult.fail(BusinessInterfaceStatus.FAIL.getCode(),"修改或添加乘客地址信息失败") ;
        }else{
            return ResponseResult.success("") ;
        }

    }

    @Override
    public int updateByPrimaryKey(PassengerAddress record) {
        return 0;
    }
}
