package com.triplus.user.controller;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class SNSController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/memberjoin/sns", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> Insert(String id, String nick, String gen, String email) {

        Character gender = null;
        String pwd = id.substring(5);

        //비밀번호는 id가 k1234~이면 pwd 1234
        //이름에 nickname을 넣었음...
        if (gen.equals("male")) {
            gender = 'M';
        } else {
            gender = 'F';
        }
        //id pwd auth name tel gender addr email bdate regdate active

        UserDto dto = new UserDto(id, pwd, "user", nick, "전화번호를 입력하세요", gender, "(주소지를 설정해주세요)", email, null, null, 'Y');
        System.out.println(dto);
        int n = userService.insert(dto);
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (n > 0) {
            result.put("result", "success");
            result.put("dto", dto);
            System.out.println("O");
        } else {
            result.put("result", "fail");
            System.out.println("X");
        }


        return result;
    }

}
