package com.triplus.schedule.service;

import com.triplus.schedule.dto.SpotDto;
import com.triplus.schedule.mapper.SpotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SpotService {

    @Autowired
    private SpotMapper spotMapper;

    public int insert(SpotDto spotDto) {

        return spotMapper.insert(spotDto);

    }

    public HashMap<String, Integer> insertAndReturn(SpotDto spotDto) {

        HashMap<String, Integer> result = new HashMap<>();

        result.put("result", spotMapper.insert(spotDto));
        result.put("spotNum", spotDto.getSpotNum());

        return result;

    }

    @Transactional(rollbackFor = Exception.class)
    public int insertAll(ArrayList<SpotDto> spotDtos) throws Exception {

        int spotResult = 0;

        for (SpotDto spotDto : spotDtos) {
            spotResult = spotMapper.insert(spotDto);
            if (spotResult < 0) {
                break;
            }
        }

        if (spotResult > 0) {
            return 1;
        } else {
            System.out.println("spotResult: " + spotResult);
            throw new Exception("DB 오류");
        }

    }

    public ArrayList<SpotDto> selectBySkdNum(int skdNum) {

        return spotMapper.selectBySkdNum(skdNum);

    }

    public int delete(int spotNum) {

        return spotMapper.delete(spotNum);

    }

}