package com.triplus.schedule.service;

import com.triplus.schedule.dto.SpotDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class SpotServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SpotService spotService;

    @Test
    public void insertAll() throws Exception {

        int skdNum = 1;

        ArrayList<SpotDto> spotDtos = new ArrayList<>();

        int day1 = 1;
        String memo1 = "test1";
        int brdNum1 = 130;
        SpotDto spotDto1 = new SpotDto(0, skdNum, day1, memo1, brdNum1);
        spotDtos.add(spotDto1);

        int day2 = 1;
        String memo2 = "test2";
        int brdNum2 = 130;
        SpotDto spotDto2 = new SpotDto(0, skdNum, day2, memo2, brdNum2);
        spotDtos.add(spotDto2);

        int day3 = 2;
        String memo3 = "test3";
        int brdNum3 = 130;
        SpotDto spotDto3 = new SpotDto(0, skdNum, day3, memo3, brdNum3);
        spotDtos.add(spotDto3);

        int n = spotService.insertAll(spotDtos);

        System.out.println(spotDtos);

        logger.info("n: " + n);
        assertEquals(1, n);

    }

    @Test
    public void insertAndReturn() throws Exception {

        int skdNum = 1;

        int day = 1;
        String memo = "test";
        int brdNum = 130;
        SpotDto spotDto = new SpotDto(0, skdNum, day, memo, brdNum);

        HashMap<String, Integer> result = spotService.insertAndReturn(spotDto);

        logger.info("result: " + result);
        assertEquals(1, (int) result.get("result"));

    }

}
