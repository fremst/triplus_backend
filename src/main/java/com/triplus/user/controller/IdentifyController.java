package com.triplus.user.controller;


import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class IdentifyController {
    @Autowired
    private UserService userService;

    //이름, 이메일 -> 회원확인
    @RequestMapping(value = "/member/identify", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identify(String name, String email){

        //이름, 이메일 회원인지 확인
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("email",email);
        UserDto dto =userService.identify(map);

        //응답
        HashMap<String, Object> map2 = new HashMap<String, Object>();

        if(dto!=null){ //회원
            map2.put("result","success");
        }else{
            map2.put("result","fail");
        }
        return  map2;
    }

    //-> 아이디 있는지
    @RequestMapping(value = "/member/identifyid", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identifyId(String id){

        UserDto dto =userService.identifyId(id);

        //응답
        HashMap<String, Object> map2 = new HashMap<String, Object>();

        if(dto!=null){ //가입한 아이디 존재o
            map2.put("result","success");
        }else{
            map2.put("result","fail");
        }
        return  map2;
    }







    //가입한 이름으로 아이디 보여주기 ShowIDView.vue
    @RequestMapping(value = "/member/showId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identify(String name){
        UserDto dto = userService.showId(name);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(dto!=null){
            map.put("result","success");
            map.put("dto",dto);
        }else{
            map.put("result","fail");
        }
        return map;
    }
}
