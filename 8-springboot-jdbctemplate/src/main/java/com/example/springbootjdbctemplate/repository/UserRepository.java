package com.example.springbootjdbctemplate.repository;

import com.example.springbootjdbctemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findAll_v1() {
        String sql = "select * from user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .sex(resultSet.getString("sex"))
                        .age(resultSet.getInt("age"))
                        .status(resultSet.getString("status"))
                        .build();
            }
        });
        return userList;
    }

    public int count() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int save(User user) {
        String sql = "insert into user(username, password, email, sex, age, status) values(?, ?, ?, ?, ?, ?)";
        int effectRowNum = jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getSex(),
                user.getAge(),
                user.getStatus()
        );
        return effectRowNum;
    }
}
