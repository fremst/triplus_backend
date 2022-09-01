package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.SpotDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SpotMapper {

    int insert(SpotDto spotDto);

//    SpotDto select(int spotNum);

    ArrayList<SpotDto> selectBySkdNum(int skdNum);

//    int update(SpotDto spotDto);

//    int delete(int spotNum);

    int deleteAllBySkdNum(int skdNum);

}