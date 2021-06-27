package com.example.springbootmultidatasources.controller;

import com.example.springbootmultidatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    @Qualifier("userServiceJdbcImpl")
    UserService userServiceJdbcImpl;


    @Autowired
    @Qualifier("userServiceJdbcTemplateImpl")
    UserService userServiceJdbcTemplateImpl;

    @Autowired
    @Qualifier("userServiceJpaImpl")
    UserService userServiceJpaImpl;

    @Autowired
    @Qualifier("userServiceMybatisImpl")
    UserService userServiceMybatisImpl;

    // ... restful api

}
