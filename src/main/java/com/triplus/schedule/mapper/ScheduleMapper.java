package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {

    int getNextSkdNum();

    int fixedInsert(ScheduleDto scheduleDto);

//    ScheduleDto select(int skdNum);
//
//    int update(ScheduleDto scheduleDto);
//
//    int delete(int skdNum);

}