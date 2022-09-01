package com.triplus.schedule.mapper;

import com.triplus.schedule.dto.CompanionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class CompanionMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanionMapper companionMapper;

    @Test
    public void insert() {

        int skdNum = 5;
        String id = "kj";

        int n = companionMapper.insert(new CompanionDto(skdNum,id));

        logger.info("n: " + n);
        assertEquals(1, n);

    }

}
