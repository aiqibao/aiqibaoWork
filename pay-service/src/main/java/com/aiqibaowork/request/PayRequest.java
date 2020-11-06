package com.aiqibaowork.request;

import lombok.Data;

/**
 * @Author:aiqibao
 * @Date:2020/11/6 9:57
 * Best wish!
 */
@Data
public class PayRequest {
    private Integer yid ;
    private Double capital;
    private Double giveFee;
    private String source;
    private Integer rechargeType;
    private Integer orderId;
    private String createUser;
}
