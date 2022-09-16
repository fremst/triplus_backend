package com.triplus.board.service;

import com.triplus.board.dto.McatDto;
import com.triplus.board.dto.PlaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class McatServiceTest {
    
    @Autowired
    McatService mcatService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void select() {
        
        int mcatNum = 1;

        McatDto mcatDto = mcatService.select(mcatNum);

        logger.info("mcatDto: " + mcatDto);
        assertNotNull(mcatDto);

    }

    @Test
    public void selectByMcatName() {

        String mcatName = "명소";

        McatDto mcatDto = mcatService.selectByMcatName(mcatName);

        logger.info("mcatDto: " + mcatDto);
        assertNotNull(mcatDto);

    }

}
