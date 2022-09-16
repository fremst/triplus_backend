package com.triplus.board.service;

import com.triplus.board.dto.FaqDto;
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
public class FaqServiceTest {

    @Autowired
    FaqService faqService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void selectAll() {
        logger.info("faqService:" + faqService);
        ArrayList<FaqDto> list = faqService.selectAll();
        logger.info("list:" + list);
        assertNotNull(list);
    }

    @Test
    public void insert() {
        logger.info("faqService:" + faqService);

        int n = faqService.insert(new FaqDto(1, "admin", "hotel", "bb", "bb"));
    }

    @Test
    public void delete() {
        int n1 = faqService.delete(2);
        assertEquals(n1, 1);
    }

    @Test
    public void select() {
        logger.info("faqService:" + faqService);
        FaqDto faqDto = faqService.select(1);
        logger.info("faqDto:" + faqDto);
        assertNotNull(faqDto);
    }
}
