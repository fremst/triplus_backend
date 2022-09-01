package com.triplus.schedule.service;

import com.triplus.schedule.dto.SpotDto;
import com.triplus.schedule.mapper.SpotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class SpotService {

    @Autowired
    private SpotMapper spotMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insert(ArrayList<SpotDto> spotDtos) throws Exception {

        int spotResult = 0;

        for(SpotDto spotDto:spotDtos){
                spotResult = spotMapper.insert(spotDto);
                if(spotResult < 0){
                    break;
                }
        }

        if (spotResult > 0){
            return 1;
        } else {
            System.out.println("spotResult: " + spotResult);
            throw new Exception("DB 오류");
        }

    }

}