package com.example.springbootjdbctemplate.service;

import com.example.springbootjdbctemplate.model.User;
import com.example.springbootjdbctemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserSevice {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll_v1() {
        return userRepository.findAll_v1();
    }

    public int count() {
        return userRepository.count();
    }

    public int save(User user) {
        return userRepository.save(user);
    }
}
