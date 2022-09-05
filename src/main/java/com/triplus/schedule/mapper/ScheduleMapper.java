package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ScheduleMapper {

    int getNextSkdNum();

    int fixedInsert(ScheduleDto scheduleDto);

    ScheduleDto select(int skdNum);
//
//    int update(ScheduleDto scheduleDto);
//
//    int delete(int skdNum);

    List<ScheduleDto> selectMySkd(String id);

    int skdCnt(String id);

}