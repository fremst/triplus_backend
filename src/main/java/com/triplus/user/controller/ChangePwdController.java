package com.triplus.user.controller;

import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class ChangePwdController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/member/changepwd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identify(String id, String pwd) {
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


        int n = userService.changePwd(map1);

        HashMap<String, Object> map2 = new HashMap<String, Object>();

        if (n > 0) {
            map2.put("result", "success");
        } else {
            map2.put("result", "fail");
        }

        return map2;


    }
}
