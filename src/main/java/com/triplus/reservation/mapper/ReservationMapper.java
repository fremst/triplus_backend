package com.triplus.reservation.mapper;

import com.triplus.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface ReservationMapper {
    int insert(ReservationDto reservationDto);

//    ReservationDto select(String oid);

//    int update(ReservationDto reservationDto);

//    int delete(String tid);

    int updateResSta(HashMap<String, String> map);


    int getRcrtCnt(HashMap<String, Object> map);

}