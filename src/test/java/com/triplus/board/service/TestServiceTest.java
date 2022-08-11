package com.triplus.board.service;

import com.triplus.test.dto.TestDto;
import com.triplus.test.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class TestServiceTest {
    @Autowired
    TestService testService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void insert() {
        logger.info("service: " + testService);
        int n = testService.insert(new TestDto("test"));
        logger.info("n: " + n);
        assertEquals(n, 1);
    }
}