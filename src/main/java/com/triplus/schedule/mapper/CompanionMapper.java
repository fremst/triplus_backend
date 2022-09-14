package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.CompanionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanionMapper {

    int insert(CompanionDto companionDto);

    // 파라미터도 dto사용함
    CompanionDto select(CompanionDto companionDto);

//
//    ArrayList<CompanionDto> selectBySkdNum(int skdNum);
//
//    int delete(int skdNum, String id);


}
