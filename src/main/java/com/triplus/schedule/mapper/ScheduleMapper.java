package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    List<ScheduleDto> selectMySkd(String id);

    int skdCnt(String id);

}
