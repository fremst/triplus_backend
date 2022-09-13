package com.triplus.reservation.controller;

import com.triplus.board.dto.PackageDto;
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

@RequestMapping("/api")
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

    @GetMapping(value = "/myreservations/{oid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getReservation(
            @PathVariable("oid") String oid
    ) {

        HashMap<String, Object> data = new HashMap<>();

        ReservationDto reservationDto = reservationService.select(oid);
        PackageDto packageDto = packageService.select(reservationDto.getBrdNum());
        ArrayList<PkgComDto> pkgComDtos = pkgComService.selectAllByOid(oid);
        PaymentDto paymentDto = paymentService.selectByOid(oid);

        data.put("title", packageDto.getTitle());
        data.put("period", new DateUtil().getPeriod(packageDto.getSDate().toLocalDate(), packageDto.getEDate().toLocalDate()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd (E)");
        data.put("sDate", simpleDateFormat.format(packageDto.getSDate()));
        data.put("eDate", simpleDateFormat.format(packageDto.getEDate()));

        data.put("tid", paymentDto.getTid());

        data.put("totPrice", paymentDto.getTotPrice());
        data.put("resSta", reservationDto.getResSta());
        data.put("pkgComDtos", pkgComDtos);

        return data;

    }

    @GetMapping(value = "/reservation/status/{resSta}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getCountByResSta(
            @PathVariable("resSta") String resSta
    ) {

        HashMap<String, Object> data = new HashMap<>();

        int n = reservationService.getCountByResSta(resSta);
        data.put("n", n);

        return data;

    }

    @PutMapping(value = {"/reservations/{oid}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> update(@PathVariable("oid") String oid, @RequestBody HashMap<String, String> resStaMap) {

        String resSta = resStaMap.get("resSta");

        int serviceResult = 0;
        HashMap<String, String> result = new HashMap<>();

        try {

            HashMap<String, String> cond = new HashMap<>();
            cond.put("oid", oid);
            cond.put("resSta", resSta);

            serviceResult = reservationService.updateResSta(cond);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (serviceResult > 0) {
                result.put("result", "success");
            } else {
                result.put("result", "fail");
            }

        }

        return result;

    }

}
