package com.example.springbootmybatisvuejs.service.impl;

import com.example.springbootmybatisvuejs.model.User;
import com.example.springbootmybatisvuejs.mapper.UserMapper;
import com.example.springbootmybatisvuejs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean delete(int id) {
        return userMapper.delete(id);
    }
}
