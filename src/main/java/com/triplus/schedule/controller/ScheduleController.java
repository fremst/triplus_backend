package com.triplus.schedule.controller;

import com.triplus.board.util.DateUtil;
import com.triplus.schedule.dto.ScheduleDto;
import com.triplus.schedule.dto.SpotDto;
import com.triplus.schedule.service.ScheduleService;
import com.triplus.schedule.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/section/schedules")
@CrossOrigin("*")
@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    SpotService spotService;

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> insert(
            String id,
            @RequestParam("sDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date sDate,
            @RequestParam("eDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date eDate,
            String destination
    ) {

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setSDate(new java.sql.Date(sDate.getTime()));
        scheduleDto.setEDate(new java.sql.Date(eDate.getTime()));
        scheduleDto.setDestination(destination);

        HashMap<String, Object> result = new HashMap<>();

        int scheduleResult = 0;

        try {

            scheduleResult = scheduleService.insert(scheduleDto, id);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (scheduleResult > 0) {

                result.put("skdNum", scheduleDto.getSkdNum());

                System.out.println(scheduleDto);

                result.put("days", new DateUtil().getDaysBetween(scheduleDto.getSDate().toLocalDate(), scheduleDto.getEDate().toLocalDate())+1);
                result.put("result", "success");

            } else {

                result.put("result", "fail");

            }

        }

        return result;

    }

    @PostMapping(value = "/spots/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> insertSpots(ArrayList<SpotDto> spotDtos) {

        HashMap<String, Object> result = new HashMap<>();

        int spotResult = 0;

        try {

            spotResult = spotService.insert(spotDtos);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (spotResult > 0) {

                result.put("result", "success");

            } else {

                result.put("result", "fail");

            }

        }

        return result;

    }

}