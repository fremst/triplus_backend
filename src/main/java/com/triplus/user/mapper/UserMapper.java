package com.triplus.user.mapper;


import com.triplus.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


@Mapper
public interface UserMapper {
    UserDto login(HashMap<String, String> map);

    int insert(UserDto dto);

    UserDto identify(HashMap<String, String> map);

    UserDto identifyId(String id);

    UserDto showId(String name);

    int changePwd(HashMap<String, String> map);
    
}
