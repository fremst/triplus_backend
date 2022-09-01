package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.CompanionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanionMapper {

    int insert(CompanionDto companionDto);

//    CompanionDto select(int skdNum, String id);
//
//    ArrayList<CompanionDto> selectBySkdNum(int skdNum);
//
//    int delete(int skdNum, String id);

}