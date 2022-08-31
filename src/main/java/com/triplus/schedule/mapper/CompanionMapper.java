package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.CompanionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanionMapper {
    int addCompanion(CompanionDto dto);

    CompanionDto find(String id);
}
