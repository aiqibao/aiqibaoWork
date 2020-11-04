package com.aiqibaowork.mapper;

import com.aiqibaowork.entity.PassengerWallet;
import org.springframework.stereotype.Service;

@Service
public interface PassengerWalletMapper {
    int insertSelective(PassengerWallet record);
}