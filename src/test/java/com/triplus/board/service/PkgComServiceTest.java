package com.triplus.board.service;

import com.triplus.board.dto.PkgComDto;
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
public class PkgComServiceTest {

    @Autowired
    PkgComService pkgComService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void service() {

        logger.info("pkgComService: " + pkgComService);
        assertNotNull(pkgComService);

    }

    @Test
    public void insert() {

        int n = pkgComService.insert(
                new PkgComDto(
                        0,
                        "INIpayTest_1660751473300",
                        "성인",
                        "동행자1",
                        "010-111-1111",
                        'M'
                )
        );

        logger.info("n: " + n);
        assertEquals(1, n);

    }

    @Test
    public void selectAllByOid() {

        String oid = "INIpayTest_1661088602313";
        ArrayList<PkgComDto> pkgComDtos = pkgComService.selectAllByOid(oid);

        logger.info("pkgComDtos: " + pkgComDtos);
        assertNotNull(pkgComDtos);

    }

}