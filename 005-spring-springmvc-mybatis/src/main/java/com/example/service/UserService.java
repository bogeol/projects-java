package com.example.service;

import com.example.model.User;
import java.util.List;

public interface UserService {

    Object postUser(User user);

    List<User> getAllUser();

    User getUser(int userId);

    int truncateTable();
}
