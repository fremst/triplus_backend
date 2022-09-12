package com.triplus.user.utils;

import com.triplus.user.controller.CreateJWT;
import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 사용자 비밀번호 암호화 클래스
 */
public class PasswordUtils {
    /**
     * 사용자 비밀번호를 암호화 합니다.
     *
     * @param pwd 비밀번호
     * @return 암호화된 비밀번호를 반환합니다.
     */
    public static String encrypt(String pwd) {

        // 암호화
        try {
            String raw = pwd;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(raw.getBytes());
            String hex = String.format("%0128x", new BigInteger(1, md.digest()));
            return hex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwd;
    }

}