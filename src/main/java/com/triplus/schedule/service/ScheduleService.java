package com.triplus.schedule.service;

import com.triplus.schedule.dto.CompanionDto;
import com.triplus.schedule.dto.ScheduleDto;
import com.triplus.schedule.mapper.CompanionMapper;
import com.triplus.schedule.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private CompanionMapper companionMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insert(ScheduleDto scheduleDto, String id) throws Exception {

        int skdNum = scheduleMapper.getNextSkdNum();
        scheduleDto.setSkdNum(skdNum);

        int scheduleResult = scheduleMapper.fixedInsert(scheduleDto);

        int companionResult = companionMapper.insert(
                new CompanionDto(skdNum,id)
        );

        if (scheduleResult > 0 && companionResult > 0){
            return 1;
        } else {
            System.out.println("scheduleResult" + scheduleResult);
            System.out.println("companionResult" + companionResult);
            throw new Exception("DB 오류");
        }

    }

}