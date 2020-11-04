package com.aiqibaowork.mapper;

import com.aiqibaowork.entity.PassengerWallet;
import org.apache.ibatis.annotations.*;

public interface PassengerWalletMapper {
    int insertSelective(PassengerWallet record);
}