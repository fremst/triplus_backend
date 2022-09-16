package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.QnaDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class BoardTest {
    @Autowired
    BoardService boardService;
    @Autowired
    QnaService qnaService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void boardTest() {
        logger.info("service: " + boardService);

        ArrayList<BoardDto> list = boardService.selectAll();
        logger.info("list size: " + list.size());
        for (BoardDto board : list) {
            System.out.println(board.toString());
            logger.info(board.toString());
        }

        assertTrue(list.size() > 1);
    }

    @Test
    public void qnaTest() {
        logger.info("service: " + qnaService);

        ArrayList<QnaDto> list = qnaService.selectAll();
        logger.info("list size: " + list.size());
        for (QnaDto board : list) {
            logger.info(board.getBrdNum() + "");
            logger.info(board.toString());
        }

        assertEquals(list.size(), 2);
    }
}