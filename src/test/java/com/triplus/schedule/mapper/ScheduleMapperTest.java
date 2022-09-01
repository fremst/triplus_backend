package com.triplus.schedule.mapper;

import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PkgImgMapper;
import com.triplus.schedule.dto.ScheduleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class ScheduleMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Test
    public void getNextSkdNum() {

        int skdNum = scheduleMapper.getNextSkdNum();

        logger.info("skdNum: " + skdNum);
        assertTrue(skdNum > 0);

    }

    @Test
    public void fixedInsert() {

        int skdNum = scheduleMapper.getNextSkdNum();

        int n = scheduleMapper.fixedInsert(new ScheduleDto(skdNum, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+1000*60*60*24*5),"서울"));
        logger.info("n: " + n);
        assertEquals(1, n);

    }

}
