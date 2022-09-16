package com.triplus.reservation.controller;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.PackageDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.PackageService;
import com.triplus.reservation.dto.PaymentDto;
import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.service.PaymentService;
import com.triplus.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/reservation-complete/{oid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getReservationDetail(
            @PathVariable("oid") String oid
    ) {

        ReservationDto reservationDto = reservationService.select(oid);
        BoardDto boardDto = boardService.select(reservationDto.getBrdNum());
        PackageDto packageDto = packageService.select(reservationDto.getBrdNum());
        PaymentDto paymentDto = paymentService.selectByOid(oid);

        HashMap<String, Object> map = new HashMap<>();

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd (E)");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String applDateTimeStr = paymentDto.getApplDate() + paymentDto.getApplTime();
        LocalDateTime applDateTime = LocalDateTime.parse(applDateTimeStr, dateTimeFormatter);
        System.out.println(applDateTime);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        map.put("title", boardDto.getTitle());
        map.put("bookerName", reservationDto.getBookerName());
        map.put("bookerTel", reservationDto.getBookerTel());
        map.put("resSta", reservationDto.getResSta());
        map.put("sDate", simpleDateFormat1.format(packageDto.getSDate()));
        map.put("eDate", simpleDateFormat1.format(packageDto.getEDate()));
        map.put("totPrice", paymentDto.getTotPrice());
        map.put("applNum", paymentDto.getApplNum());
        map.put("applDateTime", simpleDateFormat2.format(java.util.Date.from(applDateTime.atZone(ZoneId.systemDefault()).toInstant())));
        map.put("payMethod", paymentDto.getPayMethod());

        return map;

    }
}
