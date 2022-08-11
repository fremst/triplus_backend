package com.triplus.user.mapper;


import com.triplus.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


@Mapper
public interface UserMapper {
    UserDto login(HashMap<String, String> map);

    int insert(UserDto dto);
}
