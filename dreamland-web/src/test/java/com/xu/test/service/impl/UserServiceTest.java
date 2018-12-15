package com.xu.test.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xu.Application;
import com.xu.entity.User;
import com.xu.service.UserService;
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testSave(){
        User user = new User();
        user.setNickName("封剑主-叹希奇");
        user.setEmail("123456@qq.com");
        userService.regist(user);
    }
}
