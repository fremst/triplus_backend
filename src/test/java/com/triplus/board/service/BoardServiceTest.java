package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class BoardServiceTest {

    @Autowired
    BoardService boardService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void select() {
        logger.info("boardService: " + boardService);
        BoardDto boardDto = boardService.select(101);
        logger.info("boardDto: " + boardDto);
        assertNotNull(boardDto);
    }

    @Test
    public void selectAll() {
        logger.info("boardService: " + boardService);
        ArrayList<BoardDto> list = boardService.selectAll();
        logger.info("list: " + list);
        assertNotNull(list);
    }

}