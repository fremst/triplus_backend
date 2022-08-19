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

//    @Test
//    public void insert() {
//        PlaceDto placeDto = new PlaceDto(1, 2, 1, 1, "서울", "종로구", "010", 1, 1, "aa");
//        int n = placeService.insert(placeDto);
//        assertEquals(n, 1);
//
//    }

    @Test
    public void select() {
        PlaceDto placeDto = placeService.select(1);
        logger.info("placeDto:" + placeDto);
        assertNotNull(placeDto);
    }

    @Test
    public void selectAll() {
        ArrayList<PlaceDto> list = placeService.selectAll();
        logger.info("list" + list);
        assertNotNull(list);
    }

    @Test
    public void delete() {
        int n1 = placeService.delete(1);
        assertEquals(n1, 1);
    }

    @Test
    public void update() {
        //int n2 = boardService.bPlaceUpdate(new BoardDto(1, null, "asd", "asdd", "asddad", null, 0, true));
        int n3 = placeService.update(new PlaceDto(1, 1, 1, "aa", "aa", "123", 0, 0, "aaa"));
    }

    @Test
    public void select1() {
        //int n = mcatService.select("숙소");
        int n5 = scatService.select("자연관광지");
        assertEquals(n5, 1);
    }

}
