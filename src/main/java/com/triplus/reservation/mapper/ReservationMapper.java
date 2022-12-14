package com.triplus.reservation.mapper;

import com.triplus.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface ReservationMapper {
    int insert(ReservationDto reservationDto);

    ReservationDto select(String oid);

    ArrayList<ReservationDto> selectByBrdNum(int brdNum);

    ReservationDto selectByBrdNumAndId(HashMap<String, Object> map);

    int getCountByResSta(String resSta);
    
//    int update(ReservationDto reservationDto);

//    int delete(String tid);

    int updateResSta(HashMap<String, String> map);


    int getRcrtCnt(HashMap<String, Object> map);


    //내 예약보기
    List<HashMap<String, Object>> selectWant(String id);

    //내 예약수 ->헤더
    int myreservationCnt(String id);
}