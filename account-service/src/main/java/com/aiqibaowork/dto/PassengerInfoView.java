package com.aiqibaowork.dto;

import com.aiqibaowork.entity.PassengerAddress;
import com.aiqibaowork.entity.PassengerInfo;
import lombok.Data;

/**
 * @Author:aiqibao
 * @Date:2020/11/4 15:37
 * Best wish!
 */
@Data
public class PassengerInfoView {
    private PassengerInfo passengerInfo ;
    private PassengerAddress passengerAddress ;
}
