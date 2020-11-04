package com.aiqibaowork.mapper;

import com.aiqibaowork.entity.PassengerInfo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface PassengerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PassengerInfo record);

    int insertSelective(PassengerInfo record);

    PassengerInfo selectByPrimaryKey(Integer id);

    List<PassengerInfo> selectByPrimaryKeyList();

    int updateByPrimaryKeySelective(PassengerInfo record);

    int updateByPrimaryKey(PassengerInfo record);

    PassengerInfo queryPassengerInfoByPhoneNum(String phone);

    int updatePassengerBalance(Map<String, Object> map);

    void updatePassengerInfoLoginTime(Integer passengerId);

}