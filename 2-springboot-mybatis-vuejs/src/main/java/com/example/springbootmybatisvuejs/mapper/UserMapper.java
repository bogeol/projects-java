package com.example.springbootmybatisvuejs.mapper;

import com.example.springbootmybatisvuejs.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    boolean insert(@Param("user") User user);

    boolean delete(int id);

}
