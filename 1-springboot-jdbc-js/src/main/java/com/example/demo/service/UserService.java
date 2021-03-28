package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    boolean delete(int id);

    boolean update(User user);

    boolean insert(User user);
}
