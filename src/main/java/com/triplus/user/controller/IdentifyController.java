package com.triplus.user.controller;


import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class IdentifyController {
    @Autowired
    private UserService userService;

    //이름, 이메일 -> 회원확인
    @RequestMapping(value = "/member/identify", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identify(String name, String email, HttpServletRequest request) {

        /*
        //jwt token test
        String auth = request.getHeader("token");

        CreateJWT jwt = new CreateJWT();
        try {
            Map<String, Object> check = jwt.verifyJWT(auth);
            System.out.println(check);
            //{sub=user, data=kj, exp=1660843425}
        } catch (Exception e) { //오류-> 만료기간 끝
            e.printStackTrace();
            System.out.println("만료?? 오류-> 실패 응답");
        }
    */

        //이름, 이메일 회원인지 확인
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("email", email);
        UserDto dto = userService.identify(map);

        //응답
        HashMap<String, Object> map2 = new HashMap<String, Object>();

        if (dto != null) { //회원
            map2.put("result", "success");
        } else {
            map2.put("result", "fail");
        }
        return map2;
    }

    //-> 아이디 있는지
    @RequestMapping(value = "/member/identifyid", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identifyId(String id) {

        UserDto dto = userService.identifyId(id);
        System.out.println(dto);
        //응답
        HashMap<String, Object> map2 = new HashMap<String, Object>();

        if (dto != null) { //가입한 아이디 존재o
            map2.put("result", "success");
            map2.put("dto", dto);
        } else {
            map2.put("result", "fail");
        }
        return map2;
    }

    //가입한 이름으로 아이디 보여주기 ShowIDView.vue
    @RequestMapping(value = "/member/showId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identify(String name) {
        UserDto dto = userService.showId(name);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (dto != null) {
            map.put("result", "success");
            map.put("dto", dto);
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    //마이페이지 비밀번호 확인
    @RequestMapping(value = "/member/identifypwd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> identifyPwd(String id, String pwd) {
        System.out.println(id + "--------" + pwd);
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("id", id);
        try {
            String raw = pwd;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(raw.getBytes());
            String hex = String.format("%0128x", new BigInteger(1, md.digest()));
            System.out.println("hex:" + hex);

            map1.put("pwd", hex);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        UserDto dto = userService.identifyUser(map1);
        System.out.println(dto);
        HashMap<String, String> map = new HashMap<String, String>();

        if (dto != null) {
            map.put("result", "success");

        } else {
            map.put("result", "fail");
        }

        return map;
    }

    // 검색 아이디 조회(일행초대)
    @GetMapping(value = "/api/member/plan/invite/findid", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> searchId(String inviteId) {
        String searchId = userService.findId(inviteId);
        HashMap<String, String> result = new HashMap<String, String>();
        if (searchId != null) {
            result.put("result", "success");
            result.put("searchId", searchId);
        } else {
            result.put("result", "fail");
        }


        return result;


    }


}










