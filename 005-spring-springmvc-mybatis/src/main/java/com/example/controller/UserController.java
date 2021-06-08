package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController implements InitializingBean {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @GetMapping("test")
    public String index(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("date", new Date());
        model.addAttribute("randomNumbers", new Random().doubles().limit(10).boxed().collect(Collectors.toList()));
        return "user";
    }

    @GetMapping()
    @ResponseBody
    public Object getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("{userId}")
    @ResponseBody
    public Object getUser(@PathVariable(value = "userId") int userId) {
        return userService.getUser(userId);
    }

    @PostMapping()
    @ResponseBody
    public Object postUser(@RequestBody User user) {
        return userService.postUser(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.truncateTable();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());
            user.setEmail(UUID.randomUUID().toString());
            userService.postUser(user);
        }
    }
}
