package com.triplus.test.service;

import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void inserttest() {

        UserDto dto = new UserDto("kakao123", "123", "user", "강준", "전화번호", 'M', "주소지", "이메일", null, null, 'Y');
        int n = userService.insert(dto);

        assertEquals(n, 1);
    }
}
