package com.triplus.user.controller;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class UpdateController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/member/mypage/find/{id}", "api/member/mypage/find/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> find(@PathVariable("id") String id) {
        UserDto dto = userService.find(id);
        //응답
        HashMap<String, Object> result = new HashMap<String, Object>();

        if (dto != null) {
            result.put("result", "success");
            result.put("dto", dto);
        } else {
            result.put("result", "fail");
        }
        return result;
    }

    @PostMapping(value = "/member/mypage/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> update(UserDto dto) {
        System.out.println(dto);

        int n = userService.update(dto);
        System.out.println(n);

        //응답
        HashMap<String, Object> result = new HashMap<String, Object>();

        if (n > 0) {
            result.put("result", "success");

        } else {
            result.put("result", "fail");
        }
        return result;
    }


}
