package com.triplus.board.service;

import com.triplus.reservation.dto.PaymentDto;
import com.triplus.reservation.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void service() {

        logger.info("paymentService: " + paymentService);
        assertNotNull(paymentService);

    }

//    @Test
//    public void insert() {
//
//    }

    @Test
    public void select() {

        String tid = "StdpayCARDINIpayTest20220821223021447200";
        PaymentDto paymentDto = paymentService.select(tid);

        logger.info("paymentDto: " + paymentDto);
        assertNotNull(paymentDto);

    }

    @Test
    public void selectByOid() {

        String oid = "INIpayTest_1661088602313";
        PaymentDto paymentDto = paymentService.selectByOid(oid);

        logger.info("paymentDto: " + paymentDto);
        assertNotNull(paymentDto);

    }

}