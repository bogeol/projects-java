package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Object postUser(User user) {
        System.out.println(user);
        int effectRowNum = userMapper.postUser(user);
        int returnId = user.getId();
        return new HashMap<Object, Object>() {{
            put("effectRowNum", effectRowNum);
            put("returnId", returnId);
        }};
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUser(int userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public int truncateTable() {
        return userMapper.truncateTable();
    }
}
