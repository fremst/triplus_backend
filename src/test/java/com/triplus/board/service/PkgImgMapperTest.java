package com.triplus.board.service;

import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PkgImgMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PkgImgMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PkgImgMapper pkgImgMapper;

    @Test
    public void selectByBrdNum() {

        int brdNum = 103;

        ArrayList<PkgImgDto> pkgImgDtos = pkgImgMapper.selectByBrdNum(brdNum);
        logger.info("pkgImgDtos: " + pkgImgDtos);
        assertNotNull(pkgImgDtos);

    }

    @Test
    public void deleteByBrdNum() {

        int n = pkgImgMapper.deleteByBrdNum(288);

        logger.info("n: " + n);
        assertTrue(n > 1);

    }

}
