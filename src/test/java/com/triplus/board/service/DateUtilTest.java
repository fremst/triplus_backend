package com.triplus.board.service;

import com.triplus.board.util.DateUtil;
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
public class DateUtilTest {

    @Autowired
    PackageService packageService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void getPeriodTest() {

        int brdNum = 100;

        LocalDate fromDate = packageService.select(brdNum).getSDate().toLocalDate();
        LocalDate toDate = packageService.select(brdNum).getEDate().toLocalDate();
        long daysBetween = DAYS.between(fromDate, toDate);

        logger.info("fromDate: " + fromDate);
        logger.info("toDate: " + toDate);
        logger.info("daysBetween: " + new DateUtil().getDaysBetween(fromDate, toDate));
        logger.info("period: " + new DateUtil().getPeriod(fromDate, toDate));

//        String resSta = "";
//
////        LocalDate fromDate = LocalDate.now();
//        if (daysBetween <= 7) {
//            resSta = "모집완료";
//        }

    }

}
