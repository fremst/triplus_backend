package com.triplus.user.controller;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@CrossOrigin("*")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/member/login/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> login(@PathVariable("id") String id, String pwd) {

        //http://localhost:8082/triplus/api/member/login/{kj}

        // 아이디 비밀번호 여부 확인
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("pwd", pwd);

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
            result.put("token",token);
        } else { // 회원X
            result.put("result", "fail");
        }
        return result;
    }


}