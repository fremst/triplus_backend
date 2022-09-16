package com.triplus.schedule.controller;

import com.triplus.schedule.dto.ScheduleDto;
import com.triplus.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@CrossOrigin("*")
@RestController
public class MyScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping(value = "/api/member/mypage/myschedule", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> mySkd(String id) {
        List<ScheduleDto> list = scheduleService.selectMySkd(id);
        System.out.println(list);
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        return result;
    }

    @GetMapping(value = "/api/member/mypage/myscheduleCnt", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> mySkdCnt(String id) {
        int cnt = scheduleService.skdCnt(id);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("myscheduleCnt", cnt);
        return result;
    }

}
