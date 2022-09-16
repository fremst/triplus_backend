package com.triplus.schedule.service;

import com.triplus.schedule.dto.ScheduleDto;
import com.triplus.schedule.mapper.ScheduleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class ScheduleServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void insert() throws Exception{

        ScheduleDto scheduleDto = new ScheduleDto(
                0,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+1000*60*60*24*5),
                "서울");
        String id = "test";

        int n = scheduleService.insert(scheduleDto, id);
        int skdNum = scheduleDto.getSkdNum();

        logger.info("n: " + n);
        logger.info("skdNum: " + skdNum);
        assertEquals(1, n);
        assertTrue(skdNum > 0);

    }


    @Test
    public void delete() {

        int skdNum = 128;
        int n = scheduleService.delete(skdNum);
        logger.info("n: " + n);
        assertTrue(n > 0);

    }

}
