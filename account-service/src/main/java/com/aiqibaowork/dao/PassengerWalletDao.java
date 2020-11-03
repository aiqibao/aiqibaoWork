package com.aiqibaowork.dao;

import com.aiqibaowork.entity.PassengerWallet;
import com.aiqibaowork.mapper.PassengerWalletMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @Author:aiqibao
 * @Date:2020/11/2 17:27
 * Best wish!
 */
@Repository
@RequiredArgsConstructor
public class PassengerWalletDao {
    @NonNull
    private PassengerWalletMapper passengerWalletMapper ;

    public int insertSelective(PassengerWallet passengerWallet){
        return passengerWalletMapper.insertSelective(passengerWallet) ;
    }
}
