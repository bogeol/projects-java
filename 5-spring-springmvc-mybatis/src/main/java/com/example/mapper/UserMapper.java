package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {

    int postUser(User user);

    List<User> getAllUser();

    User getUser(int userId);

    int truncateTable();
}
