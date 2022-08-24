package com.triplus.reservation.controller;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.PackageDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.PackageService;
import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;

@RequestMapping("/api/section/packages")
@CrossOrigin("*")
@RestController
public class PayCompleteController {

    @Autowired
    PackageService packageService;

    @Autowired
    BoardService boardService;

    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/reservation-complete/{oid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getReservationDetail(
            @PathVariable("oid") String oid
    ) {

        ReservationDto reservationDto = reservationService.select(oid);
        BoardDto boardDto = boardService.select(reservationDto.getBrdNum());
        PackageDto packageDto = packageService.select(reservationDto.getBrdNum());
        HashMap<String, Object> map = new HashMap<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd (E)");

        map.put("title", boardDto.getTitle());
        map.put("bookerName", reservationDto.getBookerName());
        map.put("bookerTel", reservationDto.getBookerTel());
        map.put("sDate", simpleDateFormat.format(packageDto.getSDate()));
        map.put("eDate", simpleDateFormat.format(packageDto.getEDate()));

        return map;

    }
}
