package com.triplus.user.utils;

import com.triplus.user.controller.CreateJWT;
import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 사용자 접속 유효성 검사 클래스
 */
public class VerifyUtils {
    /**
     * 사용자 접속 유효성을 검사하며, 동시에 UserDTO 값을 취득합니다.
     *
     * @param userService userService 객체
     * @param id          검사할 ID
     * @param token       검사할 Token
     * @return 검사에 성공하면 DTO를 반환하고 아니라면 NULL을 반환합니다.
     */
    public static UserDto checkToken(UserService userService, String id, String token) {
        try {
            // TODO 토큰 만료 시간 체크
            // 토큰으로 ID 취득
            String tokenId = (String) new CreateJWT().verifyJWT(token).get("data");
            // 토큰으로 취득한 ID와 프론트에서 제시한 ID를 대조, 맞으면 UserDto를 반환, 아니면 null
            return id.equals(tokenId) ? userService.identifyId(id) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 사용자 접속 유효성을 검사합니다.
     *
     * @param id    검사할 ID
     * @param token 검사할 Token
     * @return 검사에 성공시 true
     */
    public static boolean checkToken(String id, String token) {
        try {
            // TODO 토큰 만료 시간 체크
            // 토큰으로 ID 취득
            String tokenId = (String) new CreateJWT().verifyJWT(token).get("data");
            return id.equals(tokenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}