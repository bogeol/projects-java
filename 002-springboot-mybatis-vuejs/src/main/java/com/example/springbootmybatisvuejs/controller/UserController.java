package com.example.springbootmybatisvuejs.controller;

import com.example.springbootmybatisvuejs.model.User;
import com.example.springbootmybatisvuejs.service.UserService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("user")
public class UserController {
    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @RequestMapping("selectAll")
    public Object selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("insert")
    public Object insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping("delete")
    public Object delete(@RequestBody int id) {
        return userService.delete(id);
    }

}
