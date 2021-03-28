package com.example.springbootmybatisvuejs.mapper;

import com.example.springbootmybatisvuejs.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void findAllTest() {
        List<User> userList = userMapper.selectAll();
        System.out.println(userList);
    }
}
