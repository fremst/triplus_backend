package com.triplus.user.controller;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@CrossOrigin("*")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/member/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> login(String id, String pwd) {

        //http://localhost:8082/triplus/api/member/login?id=kj&pwd=123

        // 아이디 비밀번호 여부 확인
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("pwd", pwd);

        UserDto dto = userService.login(map);
        System.out.println(dto);

        HashMap<String, Object> map2 = new HashMap<String, Object>(); //
        if (dto != null) { // 회원존재(로그인 정보)
            map2.put("result", "success");
            map2.put("dto", dto);
        } else { // 회원X
            map2.put("result", "fail");
        }
        return map2;
    }


}
