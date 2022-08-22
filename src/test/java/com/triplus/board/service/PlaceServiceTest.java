package com.triplus.board.service;

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
public class PlaceServiceTest {

    @Autowired
    PlaceService placeService;
    @Autowired
    BoardService boardService;
    @Autowired
    McatService mcatService;
    @Autowired
    ScatService scatService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void insert() {

        int brdNum = 130;
        int mcatNum = 1;
        int scatNum = 1;
        String region = "경기";
        String addr = "경기도 여주시 산북면 광여로 1432-14";
        String tel = "031-884-4988";
        double mapx = 127.4563679907;
        double mapy = 37.4159692517;
        String url = "http://hanapension.modoo.at";

        int n = placeService.insert(
                new PlaceDto(
                        brdNum,
                        mcatNum,
                        scatNum,
                        region,
                        addr,
                        tel,
                        mapx,
                        mapy,
                        url)
        );

        logger.info("n: " + n);
        assertEquals(n, 1);

    }

    @Test
    public void select() {

        int brdNum = 130;
        PlaceDto placeDto = placeService.select(brdNum);
        logger.info("placeDto: " + placeDto);
        assertNotNull(placeDto);

    }

    @Test
    public void selectAll() {

        ArrayList<PlaceDto> list = placeService.selectAll();
        logger.info("list: " + list);
        assertNotNull(list);

    }

    @Test
    public void selectAllByMcatName() {

        String mcatName = "명소";

        ArrayList<PlaceDto> list = placeService.selectAllByMcatNum(mcatService.selectByMcatName(mcatName).getMcatNum());
        logger.info("list: " + list);
        assertNotNull(list);

    }

    @Test
    public void update() {

        int brdNum = 130;
        int mcatNum = 1;
        int scatNum = 1;
        String region = "경기";
        String addr = "경기도 여주시 산북면 광여로 1432-14";
        String tel = "031-884-4988";
        double mapx = 127.4563679907;
        double mapy = 37.4159692517;
        String url = "http://hanapension.modoo.at";

        int n = placeService.update(
                new PlaceDto(
                        brdNum,
                        mcatNum,
                        scatNum,
                        region,
                        addr,
                        tel,
                        mapx,
                        mapy,
                        url)
                    );

        logger.info("n: " + n);
        assertEquals(n, 1);
    }

    @Test
    public void delete() {

        int n = placeService.delete(130);
        logger.info("n: " + n);
        assertEquals(n, 1);

    }


//
//    @Test
//    public void select1() {
//        //int n = mcatService.select("숙소");
//        int n5 = scatService.select("자연관광지");
//        assertEquals(n5, 1);
//    }

}
