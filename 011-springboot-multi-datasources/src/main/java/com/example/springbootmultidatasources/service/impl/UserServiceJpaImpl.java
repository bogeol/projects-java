package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.dao.secondary.repository.UserRepository;
import com.example.springbootmultidatasources.model.secondary.User;
import com.example.springbootmultidatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJpaImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveAll(List<User> userList) {
        userRepository.saveAll(userList);
    }

    @Override
    public List<User> findUsersByStatus(String status) {
        return null;
    }

    @Override
    public void truncateTableUser() {

    }
}
