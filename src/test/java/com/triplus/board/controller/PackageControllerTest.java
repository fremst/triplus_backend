package com.triplus.board.controller;

import com.triplus.board.dto.PackageDto;
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

        ArrayList<PackageDto> packageDtos = packageService.selectAll();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        for (PackageDto packageDto : packageDtos) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("brdNum", packageDto.getBrdNum());
            map.put("title", packageDto.getTitle());
            map.put("adultPrice", packageDto.getAdultPrice());
            map.put("childPrice", packageDto.getChildPrice());
            map.put("tImg", packageDto.getTImg());
            map.put("region", packageDto.getRegion());

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