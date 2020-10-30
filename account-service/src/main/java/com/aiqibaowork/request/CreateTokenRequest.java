package com.aiqibaowork.request;

import lombok.Data;

/**
 * @Author:aiqibao
 * @Date:2020/10/30 16:22
 * Best wish!
 */
@Data
public class CreateTokenRequest {
    private int type ;
    private String phoneNum ;
    private int id ;
}
