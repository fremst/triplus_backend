package com.triplus.schedule.controller;

import com.triplus.board.dto.PlaceDto;
import com.triplus.board.service.PlaceService;
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

    @Autowired
    PlaceService placeService;

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

                result.put("days", new DateUtil().getDaysBetween(scheduleDto.getSDate().toLocalDate(), scheduleDto.getEDate().toLocalDate()) + 1);
                result.put("result", "success");

            } else {

                result.put("result", "fail");

            }

        }

        return result;

    }

    @GetMapping(value = "/{skdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> select(
            @PathVariable("skdNum") int skdNum
    ) {

        HashMap<String, Object> result = new HashMap<>();

        ScheduleDto scheduleDto = scheduleService.select(skdNum);

        if (scheduleDto != null) {

            result.put("sDate", scheduleDto.getSDate());
            result.put("eDate", scheduleDto.getEDate());
            result.put("days", new DateUtil().getDaysBetween(scheduleDto.getSDate().toLocalDate(), scheduleDto.getEDate().toLocalDate()) + 1);
            result.put("destination", scheduleDto.getDestination());
            result.put("result", "success");

        } else {

            result.put("result", "fail");

        }

        return result;

    }

    @PostMapping(value = "/spot/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> insert(SpotDto spotDto) {

        HashMap<String, Object> result = new HashMap<>();

        int spotResult = spotService.insert(spotDto);

        if (spotResult > 0) {

            result.put("result", "success");

        } else {

            result.put("result", "fail");

        }

        return result;

    }

    @PostMapping(value = "/spots/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> insertAll(ArrayList<SpotDto> spotDtos) {

        HashMap<String, Object> result = new HashMap<>();

        int spotResult = 0;

        try {

            spotResult = spotService.insertAll(spotDtos);

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

    @GetMapping(value = "/spots/{skdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> selectSpots(
            @PathVariable("skdNum") int skdNum
    ) {

        HashMap<String, Object> result = new HashMap<>();

        ArrayList<SpotDto> spotDtos = spotService.selectBySkdNum(skdNum);

        if (spotDtos != null) {

            result.put("result", "success");

            ArrayList<HashMap<String, Object>> data = new ArrayList<>();

            for (SpotDto spotDto : spotDtos) {

                HashMap<String, Object> spotMap = new HashMap<>();

                spotMap.put("spotNum", spotDto.getSpotNum());
                spotMap.put("skdNum", spotDto.getSkdNum());
                spotMap.put("day", spotDto.getDay());
                spotMap.put("memo", spotDto.getMemo());

                PlaceDto placeDto = placeService.select(spotDto.getBrdNum());
                spotMap.put("title", placeDto.getTitle());
                spotMap.put("mapx", placeDto.getMapx());
                spotMap.put("mapy", placeDto.getMapy());

                data.add(spotMap);

            }

            result.put("data", data);

        } else {

            result.put("result", "fail");

        }

        return result;

    }

    @DeleteMapping(value = {"/spots/{spotNum}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> deleteSpot(@PathVariable("spotNum") int spotNum) {

        int serviceResult = 0;
        HashMap<String, String> result = new HashMap<>();

        try {

            serviceResult = spotService.delete(spotNum);

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