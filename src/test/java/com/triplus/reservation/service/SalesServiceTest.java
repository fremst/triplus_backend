package com.triplus.reservation.service;

import com.triplus.reservation.dto.BestItemsDto;
import com.triplus.reservation.dto.TotSalesPerMonthDto;
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
public class SalesServiceTest {
    @Autowired
    SalesService salesService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void getBestItems() {

        int rank = 3;

        logger.info("salesService: " + salesService);
        ArrayList<BestItemsDto> bestItems = salesService.getBestItems(rank);

        bestItems.forEach(bestItemsDto -> logger.info(bestItemsDto.getTitle()));

        assertNotNull(bestItems);

    }

    @Test
    public void getSalesPerGender() {

        logger.info("salesService: " + salesService);

        HashMap<String, Integer> data = salesService.getSalesPerGender();

        logger.info("data: " + data);

    }

    @Test
    public void getTotSalesPerMonth() {

        logger.info("salesService: " + salesService);

        ArrayList<TotSalesPerMonthDto> totSalesPerMonthDto = salesService.getTotSalesPerMonth();

        logger.info("totSalesPerMonthDto: " + totSalesPerMonthDto);

    }

}