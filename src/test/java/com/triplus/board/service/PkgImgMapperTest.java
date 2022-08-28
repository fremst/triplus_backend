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

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class PkgImgMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PkgImgMapper pkgImgMapper;

//
//    @Test
//    public void select() {
//
//        PkgImgDto pkgImgDto = pkgImgMapper.select(1);
//        logger.info("pkgImgDto: " + pkgImgDto);
//        assertNotNull(pkgImgDto);
//
//    }

    @Test
    public void selectByBrdNum() {

        int brdNum = 103;

        ArrayList<PkgImgDto> pkgImgDtos = pkgImgMapper.selectByBrdNum(brdNum);
        logger.info("pkgImgDtos: " + pkgImgDtos);
        assertNotNull(pkgImgDtos);

    }
//
//    @Test
//    public void update() {
//
//        int pkgImgNum = 22;
//        int brdNum = 100;
//
//        int n = pkgImgMapper.update(
//                new PkgImgDto(
//                        22,
//                        22,
//                        "테스트_이미지_URL_수정"
//                )
//        );
//
//        logger.info("n: " + n);
//        assertEquals(1, n);
//
//    }
//
//    @Test
//    public void delete() {
//
//        int n = pkgImgMapper.delete(23);
//
//        logger.info("n: " + n);
//        assertEquals(1, n);
//
//    }

}
