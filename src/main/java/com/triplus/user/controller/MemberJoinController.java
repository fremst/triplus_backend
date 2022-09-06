package com.triplus.user.controller;


import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class MemberJoinController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/api/memberjoin", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insertMember(UserDto dto) {
        //  UserDto dto = new UserDto(id, pwd, "user", name, tel, gender, addr, email, bDate, null, 'Y');

        try {
            System.out.println(dto);
            String raw = dto.getPwd();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(raw.getBytes());
            String hex = String.format("%0128x", new BigInteger(1, md.digest()));
            System.out.println("hex:" + hex);
            dto.setPwd(hex);

            int n = userService.insert(dto);
            System.out.println(n);
            HashMap<String, String> result = new HashMap<String, String>();
            if (n > 0) {
                result.put("result", "success");
            } else {
                result.put("result", "fail");
            }
            return result;

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;

        }

    }

}
