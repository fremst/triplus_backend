package com.triplus.user.service;


import com.triplus.user.dto.UserDto;
import com.triplus.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int insert(UserDto dto) {
        return userMapper.insert(dto);
    }

    public UserDto login(HashMap<String, String> map) {
        return userMapper.login(map);
    }

    public UserDto identify(HashMap<String, String> map){
        return userMapper.identify(map);
    }

    public UserDto identifyId(String id){
        return userMapper.identifyId(id);
    }

    public int changePwd(HashMap<String, String> map){
        return userMapper.changePwd(map);

    }


    public UserDto showId(String name){
        return userMapper.showId(name);
    }


}
