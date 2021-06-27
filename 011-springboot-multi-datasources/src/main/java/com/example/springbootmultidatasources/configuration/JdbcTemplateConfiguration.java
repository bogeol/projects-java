package com.example.springbootmultidatasources.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfiguration {

    @Primary
    @Bean
    public JdbcTemplate jdbcTemplatePrimary(@Qualifier("dataSourcePrimary") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplateSecondary(@Qualifier("dataSourceSecondary") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
