package com.example.springbootmultidatasources.service;

import com.example.springbootmultidatasources.model.secondary.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void saveAll(List<User> userList);

    List<User> findUsersByStatus(String status);

    void truncateTableUser();

}
