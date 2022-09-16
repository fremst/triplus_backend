package com.triplus.board.service;

import com.triplus.board.dto.McatDto;
import com.triplus.board.dto.ScatDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class ScatServiceTest {
    
    @Autowired
    ScatService scatService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void select() {
        
        int mcatNum = 1;
        int scatNum = 1;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("mcatNum", mcatNum);
        map.put("scatNum", scatNum);

        ScatDto scatDto = scatService.select(map);

        logger.info("scatDto: " + scatDto);
        assertNotNull(scatDto);

    }

    @Test
    public void getScatNum() {

        int mcatNum = 3;
        String scatName = "기타";

        HashMap<String, Object> scatMap = new HashMap<>();
        scatMap.put("mcatNum", mcatNum);
        scatMap.put("scatName", scatName);
        int scatNum = scatService.getScatNum(scatMap);

        logger.info("scatNum: " + scatNum);
        assertTrue(scatNum > 1);

    }

}
