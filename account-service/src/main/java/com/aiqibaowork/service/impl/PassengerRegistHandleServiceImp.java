package com.aiqibaowork.service.impl;

import com.aiqibao.dto.ResponseResult;
import com.aiqibaowork.request.GetTokenRequest;
import com.aiqibaowork.service.AuthService;
import com.aiqibaowork.service.PassengerRegistHandleService;
import com.aiqibaowork.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author:aiqibao
 * @Date:2020/10/30 15:58
 * Best wish!
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerRegistHandleServiceImp implements PassengerRegistHandleService {
    @NonNull
    private AuthService authService;


    /**
     * 乘客登入
     *
     * @param getTokenRequest
     * @return ResponseResult
     */
    @Override
    public ResponseResult handle(GetTokenRequest getTokenRequest) {
        return null;
    }

    /**
     * 乘客登出
     *
     * @param request
     * @return ResponseResult
     * @throws Exception
     */
    @Override
    public ResponseResult checout(GetTokenRequest request) throws Exception {
        String strToken = request.getToken() ;
        Claims claims = JwtUtil.parseJwt(strToken) ;
        String subject = claims.getSubject() ;
        authService.deleteToken(subject);
        return ResponseResult.success();
    }
}
