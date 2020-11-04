package com.aiqibaowork.request;

import com.aiqibaowork.dto.PassengerInfoView;
import lombok.Data;

/**
 * @Author:aiqibao
 * @Date:2020/11/4 15:34
 * Best wish!
 */
@Data
public class PassengerInfoRequest {
    private Integer id ;
    private PassengerInfoView data;
}
