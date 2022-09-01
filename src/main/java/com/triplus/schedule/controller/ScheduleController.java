package com.triplus.schedule.controller;

import com.triplus.schedule.dto.ScheduleDto;
import com.triplus.schedule.dto.SpotDto;
import com.triplus.schedule.service.ScheduleService;
import com.triplus.schedule.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public HashMap<String, Object> insert(ScheduleDto scheduleDto, String id) {

        HashMap<String, Object> result = new HashMap<>();

        int scheduleResult = 0;

        try {

            scheduleResult = scheduleService.insert(scheduleDto, id);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (scheduleResult > 0) {

                result.put("skdNum", scheduleDto.getSkdNum());
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