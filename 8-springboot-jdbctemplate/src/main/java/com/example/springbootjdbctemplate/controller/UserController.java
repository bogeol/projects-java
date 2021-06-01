package com.example.springbootjdbctemplate.controller;

import com.example.springbootjdbctemplate.model.User;
import com.example.springbootjdbctemplate.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserSevice userSevice;

    @GetMapping("findAll_v1")
    public Object findAll_v1() {
        return userSevice.findAll_v1();
    }

    @GetMapping("count")
    public Object count() {
        return userSevice.count();
    }

    @PostMapping("user")
    public Object save(@RequestBody User user) {
        return userSevice.save(user);
    }
}
