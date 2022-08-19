package com.triplus.reservation.service;

import com.triplus.reservation.dto.ReservationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void insert() {
        logger.info("reservationService: " + reservationService);
        int n = reservationService.insert(
                new ReservationDto(
                        "1",
                        100,
                        "test",
                        "예약자",
                        new Date(2000, 1, 1),
                        "010-8765-4321",
                        "booker@triplus.com",
                        0,
                        "예약"
                ));

        logger.info("n: " + n);
        assertEquals(n, 1);
    }

}