package com.example.springbootcontrollerrestcontroller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "spring") String name) {
        return "hello: " + name;
    }
}
