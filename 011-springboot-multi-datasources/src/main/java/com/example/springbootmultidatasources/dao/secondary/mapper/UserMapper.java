package com.example.springbootmultidatasources.dao.secondary.mapper;

import com.example.springbootmultidatasources.model.secondary.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    void saveAll(List<User> userList);

    List<User> findUsersByStatus(String status);

    void truncateTableUser();
}
