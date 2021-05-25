package com.example.springbootmybatisreactjs.service;

import com.example.springbootmybatisreactjs.mapper.UserMapper;
import com.example.springbootmybatisreactjs.model.User;
import com.fasterxml.jackson.core.util.InternCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public User getUser(int userId) {
        return userMapper.getUser(userId);
    }

    public Map postUser(User user) {
        Map map = new HashMap();
        int effectRowNum = userMapper.postUser(user);
        int returnId = user.getId();
        boolean result = false;
        if (effectRowNum > 0) {
            result = true;
        }
        map.put("effectRowNum", effectRowNum);
        map.put("returnId", returnId);
        map.put("result", result);
        System.out.println(map);
        return map;
    }

    public boolean putUser(User user) {
        return userMapper.putUser(user);
    }

    public boolean deleteUser(int userId) {
        return userMapper.deleteUser(userId);
    }

    public int truncateTable(String tableName) {
        return userMapper.truncateTable(tableName);
    }


}
