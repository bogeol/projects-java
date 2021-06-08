package com.example.springbootmybatisreactjs.controller;

import com.example.springbootmybatisreactjs.model.User;
import com.example.springbootmybatisreactjs.service.UserService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.mscapi.CPublicKey;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@CrossOrigin
public class UserController implements InitializingBean {
    @Autowired
    UserService userService;

    @GetMapping("user")
    public Object getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("user/{userId}")
    public Object getUser(@PathVariable("userId") int userId) {
        return userService.getUser(userId);
    }

    @PostMapping("user")
    public Object postUser(@RequestBody User user) {
        return userService.postUser(user);
    }

    @PutMapping("user")
    public Object putUser(@RequestBody User user) {
        return userService.putUser(user);
    }

    @DeleteMapping("user/{userId}")
    public Object deleteUser(@PathVariable("userId") int userId) {
        return userService.deleteUser(userId);
    }

    /**
     * result: 0 成功 / -1 失败
     */
    @PostMapping("truncateTable")
    public Object truncateTable(@RequestParam("tableName") String tableName, @RequestParam("truncate") boolean truncate) {
        if (truncate) {
            return userService.truncateTable(tableName);
        } else {
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.truncateTable("user");
        IntStream.range(1, 7).forEach(x -> {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());
            user.setEmail(UUID.randomUUID().toString());
            user.setSex(UUID.randomUUID().toString());
            user.setAge(new Random().nextInt(20) + 1);
            user.setStatus("normal");
            userService.postUser(user);
        });
    }

}
