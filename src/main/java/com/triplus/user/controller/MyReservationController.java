package com.triplus.user.controller;

import com.triplus.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
public class MyReservationController {

    @Autowired
    ReservationService reservationService;

    //내예약
    @GetMapping(value = "/api/member/mypage/reservation", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> selectAll(String id) {
        List<HashMap<String, Object>> list = reservationService.selectWant(id);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("list", list);

        return result;
    }

    //내 예약수
    @GetMapping(value = "/api/member/mypage/reservationCnt", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> cnt(String id) {
        int cnt = reservationService.myreservationCnt(id);

        HashMap<String, Object> result = new HashMap<String, Object>();

        result.put("result", "success");
        result.put("reservationCnt", cnt);


        return result;
    }

}
