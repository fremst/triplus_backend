package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PacakageResStaTest {

    @Autowired
    PackageService packageService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void getBoardResSta() {

        int brdNum = 101;

        String resSta = "";

        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = packageService.select(brdNum).getSDate().toLocalDate();
        long daysBetween = DAYS.between(fromDate, toDate);

        logger.info("fromDate: " + fromDate);
        logger.info("toDate: " + toDate);
        logger.info("daysBetween: " + daysBetween);

        int recrtCnt = packageService.select(brdNum).getRecrtCnt();
        
        if (daysBetween <= 7) {
            resSta = "모집완료";
        }

    }

}
