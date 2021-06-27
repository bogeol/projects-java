package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.dao.secondary.mapper.UserMapper;
import com.example.springbootmultidatasources.model.secondary.User;
import com.example.springbootmultidatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMybatisImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void saveAll(List<User> userList) {
        userMapper.saveAll(userList);
    }

    @Override
    public List<User> findUsersByStatus(String status) {
        return userMapper.findUsersByStatus(status);
    }

    @Override
    public void truncateTableUser() {
        userMapper.truncateTableUser();
    }
}
