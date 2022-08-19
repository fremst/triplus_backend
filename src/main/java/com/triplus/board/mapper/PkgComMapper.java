package com.triplus.board.mapper;

import com.triplus.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface PkgComMapper {

    int getRcrtCnt(HashMap<String, Object> map);

}