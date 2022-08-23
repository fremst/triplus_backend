package com.triplus.reservation.utils;

import com.triplus.user.controller.CreateJWT;
import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 사용자 접속 유효성 검사 클래스
 */
public class VerifyUtils {

    public static UserDto checkToken(UserService userService, String id, String token)
    {
        try {
            // TODO 토큰 만료 시간 체크

            // 토큰으로 ID 취득
            String tokenId = (String)new CreateJWT().verifyJWT(token).get("data");

            // 토큰으로 취득한 ID와 프론트에서 제시한 ID를 대조, 맞으면 UserDto를 반환, 아니면 null
            return id.equals(tokenId) ? userService.identifyId(id) : null;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
