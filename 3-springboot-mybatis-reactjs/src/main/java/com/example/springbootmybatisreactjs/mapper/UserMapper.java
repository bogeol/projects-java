package com.example.springbootmybatisreactjs.mapper;

import com.example.springbootmybatisreactjs.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUser();

    User getUser(int userId);

    int postUser(User user);

    boolean putUser(User user);

    boolean deleteUser(int userId);

    int truncateTable(String tableName);

}
