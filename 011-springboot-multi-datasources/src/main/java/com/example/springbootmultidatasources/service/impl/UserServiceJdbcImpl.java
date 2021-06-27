package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.model.secondary.User;
import com.example.springbootmultidatasources.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJdbcImpl implements UserService {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void saveAll(List<User> userList) {

    }

    @Override
    public List<User> findUsersByStatus(String status) {
        return null;
    }

    @Override
    public void truncateTableUser() {

    }
}
