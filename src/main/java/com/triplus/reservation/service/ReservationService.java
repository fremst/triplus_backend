package com.triplus.reservation.service;

import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    public int insert(ReservationDto reservationDto) {
        return reservationMapper.insert(reservationDto);
    }

    public int updateResSta(HashMap<String, String> map) {
        return reservationMapper.updateResSta(map);
    }

}