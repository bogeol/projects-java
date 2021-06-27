package com.example.springbootmultidatasources.dao.primary.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ModelJdbcTemplate {

    @Autowired
    @Qualifier("jdbcTemplatePrimary")
    JdbcTemplate jdbcTemplate;

    public String selectVersion() {
        String sql = "select version()";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
