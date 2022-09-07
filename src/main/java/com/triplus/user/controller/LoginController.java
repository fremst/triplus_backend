package com.triplus.user.controller;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


@CrossOrigin("*")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/member/login/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> login(@PathVariable("id") String id, String pwd) {

        // 아이디 비밀번호 여부 확인
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        // 입력한 비밀번호 -> 암호화해서 테이블과 일치 여부 확인
        try {
            String raw = pwd;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(raw.getBytes());
            String hex = String.format("%0128x", new BigInteger(1, md.digest()));
            System.out.println("hex:" + hex);

            params.put("pwd", hex);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }


        UserDto dto = userService.login(params);
        System.out.println(dto);

        HashMap<String, Object> result = new HashMap<String, Object>(); //
        if (dto != null) { // 회원존재(로그인 정보)
            //토큰생성
            CreateJWT jwt = new CreateJWT();
            String token = jwt.createToken(id);
            System.out.println(token);

            result.put("result", "success");
            result.put("dto", dto);
            result.put("token", token);
        } else { // 회원X
            result.put("result", "fail");
        }
        return result;
    }


}