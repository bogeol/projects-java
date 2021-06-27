package com.example.springbootjdbcspringjdbc.repository.impl;

import com.example.springbootjdbcspringjdbc.model.Model;
import com.example.springbootjdbcspringjdbc.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("modelRepositoryImpl")
public class ModelRepositoryImpl implements ModelRepository {

    @Autowired
    @Qualifier("driverManagerDataSource")
    DriverManagerDataSource driverManagerDataSource;


    /**
     * 对比【 Java - JDBC(jdbc) / Spring - JDBC(spring-jdbc) 】流程
     */
    /**
     * Java - JDBC流程：
     * - 注册数据库驱动 【Class.forName("com.mysql.jdbc.Driver");】
     * - 配置、获取数据库连接 【DriverManager.getConnection(url, username, password)】
     * - 配置语句 > 执行语句 > 返回值 【Statement > execute() > ResultSet】
     * - 关闭所有资源 【close()】
     */

    /**
     * Spring - JDBC流程：
     * - 配置 【@Configuration - @Bean】
     * - 注册数据库驱动 + 配置、获取数据库连接 【DriverManagerDatasource.getConnection()】
     * - ...同上
     */

    @Override
    public Map<String, Object> save(Model model) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, Object> map = null;
        try {
            /**
             * TODO:
             * - jbdc / spring jdbc
             * - 连接池？
             * - 单条操作 / 批量操作
             * - return > ResultSet
             */
            connection = driverManagerDataSource.getConnection();
            System.out.println("--------------------------");
            System.out.println("0.(register) / 1.(connection) driverManagerDataSource.getConnection();");
            String sql = "insert into model(`key`, `value`, `remark`) values(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getKey());
            preparedStatement.setString(2, model.getValue());
            preparedStatement.setString(3, model.getRemark());
            int effectCount = preparedStatement.executeUpdate();
            if (effectCount > 0) {
                map = new HashMap<>();
                map.put("effectCount", effectCount);
            }
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                // this func only one
                map.put("getGeneratedKeys", resultSet.getInt(1));
            }
            System.out.println("2. preparedStatement.execute();");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("3. close resources");
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("    connection.close();");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    System.out.println("    preparedStatement.close();");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    System.out.println("    resultSet.close();");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        }
        return map;
    }

    @Override
    public Model getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Model model = null;
        try {
            connection = driverManagerDataSource.getConnection();
            String sql = "select * from `model` where `id` = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt(1));
                model.setKey(resultSet.getString(2));
                model.setValue(resultSet.getString(3));
                model.setRemark(resultSet.getString(4));
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return model;
    }

    @Override
    public boolean updateById(int id, Model model) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int effectCount = 0;
        try {
            connection = driverManagerDataSource.getConnection();
            String sql = "update `model` set `key`=?, `value`=?, `remark`=? where `id`=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getKey());
            preparedStatement.setString(2, model.getValue());
            preparedStatement.setString(3, model.getRemark());
            preparedStatement.setInt(4, id);
            effectCount = preparedStatement.executeUpdate();
            if (effectCount > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int effectCount = 0;
        try {
            connection = driverManagerDataSource.getConnection();
            String sql = "delete from `model` where `id`=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            effectCount = preparedStatement.executeUpdate();
            if (effectCount > 0) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> map = null;
        List<Model> tempModelList = null;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String sql = "select * from `model`";
            resultSet = statement.executeQuery(sql);
            /**
             * 假设resultSet有个**访问指针**：
             * resultSet.last() # 移动指针到尾部
             * resultSet.first() # 移动指针到头部
             * resultSet.beforeFirst() # 移动指针到头部前面
             *
             * 百度：
             * - 默认的 ResultSet 对象仅有一个向前移动的**光标**，必须将ResultSet指定为可滚动的
             *
             * 参考：
             * - https://blog.csdn.net/u014726937/article/details/51741617/
             */
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                map = new HashMap<>();
                tempModelList = new ArrayList<>();
            }
            while (resultSet.next()) {
                Model temp = new Model();
                temp.setId(resultSet.getInt(1));
                temp.setKey(resultSet.getString(2));
                temp.setValue(resultSet.getString(3));
                temp.setRemark(resultSet.getString(4));
                tempModelList.add(temp);
            }
            map.put("effectCount", tempModelList.size()); // 此时resultSet的指针已经遍历到末尾了
            map.put("resultSet", tempModelList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return map;
    }

    @Override
    public boolean deleteAll() {
        Connection connection = null;
        Statement statement = null;
        boolean result = false;
        try {
            connection = driverManagerDataSource.getConnection();
            statement = connection.createStatement();
            String sql = "truncate `model`";
            statement.execute(sql);
            // TODO return effect row count?
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return result;
    }


}
