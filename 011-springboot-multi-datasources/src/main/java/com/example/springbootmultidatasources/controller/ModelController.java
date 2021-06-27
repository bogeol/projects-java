package com.example.springbootmultidatasources.controller;

import com.example.springbootmultidatasources.service.ModelService;
import com.example.springbootmultidatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("model")
public class ModelController {

    /**
     * TODO
     * - contructor based injection ★
     * - setter based injection
     * - field based injection
     */

    @Autowired
    @Qualifier("modelServiceJdbcImpl")
    ModelService modelServiceJdbcImpl;


    @Autowired
    @Qualifier("modelServiceJdbcTemplateImpl")
    ModelService modelServiceJdbcTemplateImpl;

    @Autowired
    @Qualifier("modelServiceJpaImpl")
    ModelService modelServiceJpaImpl;

    @Autowired
    @Qualifier("modelServiceMybatisImpl")
    ModelService modelServiceMybatisImpl;

    // ... restful api

}
