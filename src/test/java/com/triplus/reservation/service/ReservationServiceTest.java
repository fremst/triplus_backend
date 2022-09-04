package com.triplus.reservation.service;

import com.triplus.reservation.dto.ReservationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
                        "010-8765-4321",
                        "booker@triplus.com",
                        0,
                        "예약",
                        null
                ));

        logger.info("n: " + n);
        assertEquals(n, 1);
    }

    @Test
    public void select() {

        String oid = "INIpayTest_1660879841115";

        logger.info("reservationService: " + reservationService);
        ReservationDto reservationDto = reservationService.select(oid);

        logger.info("reservationDto: " + reservationDto);
        assertNotNull(reservationDto);

    }
}