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
public class MemberJoinController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/api/memberjoin", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insertMember(UserDto dto) {
        //  UserDto dto = new UserDto(id, pwd, "user", name, tel, gender, addr, email, bDate, null, 'Y');
        System.out.println(dto);
        int n = userService.insert(dto);
        HashMap<String, String> result = new HashMap<String, String>();
        if (n > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }

        return result;
    }

}
