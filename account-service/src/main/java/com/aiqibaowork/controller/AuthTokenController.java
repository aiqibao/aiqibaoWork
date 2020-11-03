package com.aiqibaowork.controller;

import com.aiqibaowork.constant.AccountStatusCode;
import com.aiqibaowork.dto.ResponseResult;
import com.aiqibaowork.request.CheckTokenRequest;
import com.aiqibaowork.request.CreateTokenRequest;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.service.AuthService;
import com.aiqibaowork.service.PassengerRegistHandleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * token校验
 * @Author:aiqibao
 * @Date:2020/10/30 14:49
 * Best wish!
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthTokenController {
    @NonNull
    private AuthService authService;

    @NonNull
    private PassengerRegistHandleService passengerRegistHandleService;

    private static final String ONE = "1" ;
        @PostMapping(value = "/checkToken")
    public ResponseResult checkToken(@RequestBody CheckTokenRequest request){
        if (null == request.getToken()){
            return ResponseResult.fail(1, AccountStatusCode.TOKEN_IS_EMPTY.getValue());
        }
        String code = authService.checkToken(request.getToken()) ;
        if (ObjectUtils.nullSafeEquals(ONE,code)){
            log.error("token已经失效");
            return ResponseResult.fail(1,"Token已失效") ;
        }else{
            log.info("成功");
            return ResponseResult.success() ;
        }
    }

    /**
     * 生成token
     * @param request
     * @return ResponseResult实例
     */
    @PostMapping(value = "/createToken")
    public ResponseResult createToken(@RequestBody CreateTokenRequest request){
        //身份_电话号码_id
        String subject = request.getType()+"_"+request.getPhoneNum()+"_"+request.getId() ;
        return ResponseResult.success(authService.createToken(subject)) ;
    }

    @PostMapping(value = "/checkout")
    public ResponseResult checkout(@RequestBody GetTokenRequest request) throws Exception {
        String token = request.getToken() ;

        if (StringUtils.isEmpty(token)){
            return ResponseResult.fail(AccountStatusCode.TOKEN_IS_EMPTY.getCode(),AccountStatusCode.TOKEN_IS_EMPTY.getValue()) ;
        }else{
            return passengerRegistHandleService.checout(request) ;
        }
    }

}
