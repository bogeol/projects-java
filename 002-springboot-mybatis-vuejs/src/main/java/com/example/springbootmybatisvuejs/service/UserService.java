package com.example.springbootmybatisvuejs.service;

import com.example.springbootmybatisvuejs.model.User;

import java.util.List;

public interface UserService {

    List<User> selectAll();

    boolean insert(User user);

    boolean delete(int id);
}
