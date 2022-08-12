package com.triplus.board.service;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService service;

    @Test
    public void insert() {
        logger.info("service: " + service);

        int n = service.insert(new UserDto("KJtest", "123", "admin", "이강준", "010-111-2222", 'M', "서울", "dlrkdwnszz@gmail.com", new Date(2020, 01, 01), null, 'Y'));
        logger.info("n: " + n);

        assertEquals(n, 1);
    }
}