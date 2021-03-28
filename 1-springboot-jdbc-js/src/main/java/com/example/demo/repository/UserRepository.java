package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD
 */

/**
 * TODO
 * - Connection连接是否能复用？
 */
@Repository
public class UserRepository {


    /**
     * 注意: ${} 取配置文件属性值 [别搞错了]
     */
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public boolean insert(User user) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            // TODO SQL insert 【忘记了】
            String sql = "insert into user values(?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getStatus());
            System.out.println(preparedStatement.toString());
            // TODO jdbc insert -> executeUpdate()
            int res = preparedStatement.executeUpdate();
            flag = res != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }

    public boolean delete(int id) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "update user set status=-1 where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();
            flag = res != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }

    public boolean update(User user) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "update user set name=?, age=?, phone=?, email=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getId());
            int res = preparedStatement.executeUpdate();
            flag = res != 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }

    public List<User> selectAll() {
        List<User> userList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String sql = "select * from user where status=1 order by id ASC";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                if (resultSet.getString("id") != "") {
                    user.setId(Integer.valueOf(resultSet.getString("id")));
                }
                if (resultSet.getString("name") != "") {
                    user.setName(resultSet.getString("name"));
                }
                if (resultSet.getString("age") != "") {
                    user.setAge(Integer.valueOf(resultSet.getString("age")));
                }
                if (resultSet.getString("phone") != "") {
                    user.setPhone(resultSet.getString("phone"));
                }
                if (resultSet.getString("email") != "") {
                    user.setEmail(resultSet.getString("email"));
                }
                if (resultSet.getString("status") != "") {
                    user.setStatus(Integer.parseInt(resultSet.getString("status")));
                }
                userList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return userList;
    }

    public User selectUserById(int id) {
        User user = new User();

        return user;
    }
}
