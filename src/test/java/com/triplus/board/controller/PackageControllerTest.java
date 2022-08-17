package com.triplus.board.controller;

import com.triplus.board.dto.PackageWithBoardDto;
import com.triplus.board.service.PackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PackageControllerTest {

    @Autowired
    PackageService packageService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void service() {

        logger.info("packageService: " + packageService);
        assertNotNull(packageService);

    }

    @Test
    public void selectAll() {

        ArrayList<PackageWithBoardDto> packageWithBoardList = packageService.selectAllWithBoard();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        for (PackageWithBoardDto dto : packageWithBoardList) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("brdNum", dto.getBrdNum());
            map.put("title", dto.getTitle());
            map.put("adultPrice", dto.getAdultPrice());
            map.put("childPrice", dto.getChildPrice());
            map.put("tImg", dto.getTImg());
            map.put("region", dto.getRegion());

            list.add(map);

        }

        System.out.println(list);

    }

    @Test
    public void getRcrtSta() {

        int remDays = 22;
        int rcrtCnt = 40;
        int vacancy = 9;

        logger.info("remDays: " + remDays);
        logger.info("vacancy: " + vacancy);

        String rcrtSta = "모집중";
        if (remDays <= 7 + 14 || vacancy <= rcrtCnt * 0.2) {
            rcrtSta = "마감임박";
        }
        if (remDays <= 7 || vacancy == 0) {
            rcrtSta = "모집완료";
        }

        logger.info("rcrtSta: " + rcrtSta);

    }


}