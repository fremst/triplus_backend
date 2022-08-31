package com.triplus.schedule.service;

import com.triplus.schedule.dto.ScheduleDto;
import com.triplus.schedule.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    public List<ScheduleDto> selectMySkd(String id) {
        return scheduleMapper.selectMySkd(id);
    }

    public int skdCnt(String id) {
        return scheduleMapper.skdCnt(id);
    }
}
