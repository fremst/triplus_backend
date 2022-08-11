package com.triplus.test.mapper;

import com.triplus.test.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    int insert(TestDto dto);
}
