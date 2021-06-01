package com.example.springbootspringdatajpa.controller;

import com.example.springbootspringdatajpa.model.User;
import com.example.springbootspringdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "helloworld";
    }

    @PostMapping("user")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("user/{userId}")
    public Object getUserByUserId(@PathVariable("userId") int userId) {
        return userService.findUserByUserId(userId);
    }

    @GetMapping("user")
    public Object getAlUser(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNumber == null && pageSize == null) {
            return userService.findAll();
        } else {
            return userService.findAllByPagingAndSorting(pageNumber, pageSize);
        }

    }

}
