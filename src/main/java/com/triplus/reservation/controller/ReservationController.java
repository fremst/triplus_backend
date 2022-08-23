package com.triplus.reservation.controller;

import com.triplus.board.dto.PackageWithBoardDto;
import com.triplus.board.dto.PkgComDto;
import com.triplus.board.service.PackageService;
import com.triplus.board.service.PkgComService;
import com.triplus.board.util.DateUtil;
import com.triplus.reservation.dto.PaymentDto;
import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.service.PaymentService;
import com.triplus.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/myreservations")
@CrossOrigin("*")
@RestController
public class ReservationController {

    @Autowired
    PackageService packageService;

    @Autowired
    PkgComService pkgComService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/{oid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getReservation(
            @PathVariable("oid") String oid
    ) {

        HashMap<String, Object> data = new HashMap<>();

        ReservationDto reservationDto = reservationService.select(oid);
        PackageWithBoardDto packageWithBoardDto = packageService.selectWithBoard(reservationDto.getBrdNum());
        ArrayList<PkgComDto> pkgComDtos = pkgComService.selectAllByOid(oid);
        PaymentDto paymentDto = paymentService.selectByOid(oid);

        data.put("title", packageWithBoardDto.getTitle());
        data.put("period", new DateUtil().getPeriod(packageWithBoardDto.getSDate().toLocalDate(), packageWithBoardDto.getEDate().toLocalDate()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd (E)");
        data.put("sDate", simpleDateFormat.format(packageWithBoardDto.getSDate()));
        data.put("eDate", simpleDateFormat.format(packageWithBoardDto.getEDate()));

        data.put("tid", paymentDto.getTid());

        data.put("totPrice", paymentDto.getTotPrice());
        data.put("resSta", reservationDto.getResSta());
        data.put("pkgComDtos", pkgComDtos);

        return data;

    }

}
