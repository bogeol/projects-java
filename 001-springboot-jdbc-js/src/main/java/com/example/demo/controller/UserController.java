package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody

@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("selectAll")
    public Object selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("random")
    public Object random() {
        List list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Math.random());
        }
        return list;
    }

    @RequestMapping("delete")
    public boolean delete(int id) {
        return userService.delete(id);
    }

    // int id, String name, int age, String phone, String email
    // HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse

    // TODO 问题: 【当XHR时：application/json】
    // - @RequestBody【请求：获取JSON】【JSON->define model】
    // - @ResponseBody【响应：返回JSON】

    @RequestMapping("update")
    public boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping("insert")
    public boolean insert(@RequestBody User user) {
        return userService.insert(user);
    }
}
