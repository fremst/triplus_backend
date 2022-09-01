package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.SpotDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class SpotMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SpotMapper spotMapper;

    @Test
    public void insert() {

        int skdNum = 1;
        int day = 1;
        String memo = "test";
        int brdNum = 130;

        int n = spotMapper.insert(new SpotDto(0,1, day, memo, brdNum));
        logger.info("n: " + n);
        assertEquals(1, n);

    }

    @Test
    public void selectBySkdNum() {

        int skdNum = 1;
        ArrayList<SpotDto> SpotDtos = spotMapper.selectBySkdNum(skdNum);
        logger.info("SpotDtos: " + SpotDtos);
        assertNotNull(SpotDtos);

    }

    @Test
    public void deleteAllBySkdNum() {

        int skdNum = 1;
        int n = spotMapper.deleteAllBySkdNum(skdNum);
        logger.info("n: " + n);
        assertTrue(n > 0);

    }

}
