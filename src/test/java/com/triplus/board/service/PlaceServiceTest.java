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
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PlaceServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    PlaceService placeService;

    @Autowired
    McatService mcatService;

    @Autowired
    ScatService scatService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void insert() throws Exception {

        String title = "test_title";
        String contents = "test_overview";
        byte[] tImg = null;
        boolean published = true;

        String mcatName = "숙소";
        String scatName = "펜션/민박";

        String region = "경기";
        String addr = "경기도 여주시 산북면 광여로 1432-14";
        String tel = "031-884-4988";
        double mapx = 127.4563679907;
        double mapy = 37.4159692517;
        String url = "http://hanapension.modoo.at";

        int mcatNum = mcatService.selectByMcatName(mcatName).getMcatNum();

        HashMap<String, Object> scatMap = new HashMap<>();
        scatMap.put("mcatNum", mcatNum);
        scatMap.put("scatName", scatName);
        int scatNum = scatService.getScatNum(scatMap);

        int n = placeService.insert(
                new PlaceDto(
                        0,
                        "admin",
                        title,
                        contents,
                        tImg,
                        null,
                        0,
                        published,
                        mcatNum,
                        scatNum,
                        region,
                        addr,
                        tel,
                        mapx,
                        mapy,
                        url
                )
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
    public void selectAllById() {

        String id = "kj";

        ArrayList<PlaceDto> list = placeService.selectAllById(id);
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
    public void update() throws Exception {

        int brdNum = 174;

        String title = "1test_title";
        String contents = "1test_overview";
        byte[] tImg = null;
        boolean published = false;

        String mcatName = "숙소";
        String scatName = "펜션/민박";

        String region = "1경기";
        String addr = "1경기도 여주시 산북면 광여로 1432-14";
        String tel = "1031-884-4988";
        double mapx = 9127.4563679907;
        double mapy = 937.4159692517;
        String url = "1http://hanapension.modoo.at";

        int mcatNum = mcatService.selectByMcatName(mcatName).getMcatNum();

        HashMap<String, Object> scatMap = new HashMap<>();
        scatMap.put("mcatNum", mcatNum);
        scatMap.put("scatName", scatName);
        int scatNum = scatService.getScatNum(scatMap);

        int n = placeService.update(
                new PlaceDto(
                        brdNum,
                        null,
                        title,
                        contents,
                        tImg,
                        null,
                        boardService.select(brdNum).getHit(),
                        published,
                        mcatNum,
                        scatNum,
                        region,
                        addr,
                        tel,
                        mapx,
                        mapy,
                        url
                )
        );

        logger.info("n: " + n);
        assertEquals(n, 1);
    }

    @Test
    public void delete() throws Exception {

        int brdNum = 212;

        int n = placeService.delete(brdNum);
        logger.info("n: " + n);
        assertEquals(n, 1);

    }

}
