package com.example.springbootmultidatasources.dao.primary.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class ModelJdbc {

    @Autowired
    @Qualifier("dataSourcePrimary")
    DataSource dataSource;

    public String selectVersion() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String version = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "select version()";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                version = resultSet.getString(1);
                break;
            }
        } catch (SQLException sqlException) {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            DataSourceUtils.releaseConnection(connection, dataSource);
            sqlException.printStackTrace();
        } finally {
            //--------------------------------- 参考JdbcTemplate中方法
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            DataSourceUtils.releaseConnection(connection, dataSource); // **释放JDBC连接到连接池中**，并不是关闭连接
            //---------------------------------
        }

        return version;
    }

}
