package com.aiqibaowork.request;

import lombok.Data;

/**
 * 获取token请求
 * @Author:aiqibao
 * @Date:2020/10/30 15:53
 * Best wish!
 */
@Data
public class GetTokenRequest {
    private String phoneNum ;

    private String verifyCode ;

    private Integer id ;

    private Integer type ;

    private Integer identityStatus ;

    private String equipTye ;

    private Double longitude;

    private Double latitude;

    private Double speed;

    private Double accuracy;

    private Double direction;

    private Double height;

    private String city;

    private String token;

    private String registerSource;

    private String marketChannel;
}
