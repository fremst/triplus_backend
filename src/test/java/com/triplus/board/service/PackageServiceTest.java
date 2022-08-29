package com.triplus.board.service;

import com.triplus.board.dto.PackageDto;
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
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PackageServiceTest {

    @Autowired
    PackageService packageService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void service() {

        logger.info("packageService: " + packageService);
        assertNotNull(packageService);

    }

    @Test
    public void select() {

        PackageDto packageDto = packageService.select(100);
        logger.info("packageDto: " + packageDto);
        assertNotNull(packageDto);

    }

    @Test
    public void selectAll() {

        ArrayList<PackageDto> list = packageService.selectAll();
        logger.info("list: " + list);
        assertNotNull(list);

    }

    @Test
    public void getRcrtStaTotCnt() {

        int expected = 9;

        HashMap<String, Object> map = new HashMap<>();

        map.put("resSta", "'대기', '확정'");
        map.put("brdNum", 100);

        int resStaTotCnt = packageService.getRcrtTotCnt(map);
        logger.info("resStaTotCnt: " + resStaTotCnt);

        assertEquals(expected, resStaTotCnt);
    }

}