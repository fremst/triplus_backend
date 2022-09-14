package com.triplus.reservation.service;

import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    public int insert(ReservationDto reservationDto) {

        return reservationMapper.insert(reservationDto);

    }

    public ReservationDto select(String oid) {

        return reservationMapper.select(oid);

    }

    public ArrayList<ReservationDto> selectByBrdNum(int brdNum) {

        return reservationMapper.selectByBrdNum(brdNum);

    }

    public ReservationDto selectBybrdNumAndId(HashMap<String, Object> cond) {

        return reservationMapper.selectByBrdNumAndId(cond);

    }

    public int getCountByResSta(String resSta) {

        return reservationMapper.getCountByResSta(resSta);

    }

    /**
     * @param map oid, resSta 입력
     */
    public int updateResSta(HashMap<String, String> map) {

        return reservationMapper.updateResSta(map);

    }


    // 내 예약보기
    public List<HashMap<String, Object>> selectWant(String id) {
        return reservationMapper.selectWant(id);
    }

    public int myreservationCnt(String id) {
        return reservationMapper.myreservationCnt(id);
    }

}