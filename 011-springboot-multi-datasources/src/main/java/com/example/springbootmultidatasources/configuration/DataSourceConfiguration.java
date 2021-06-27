package com.example.springbootmultidatasources.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties dataSourcePropertiesPrimary() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource dataSourcePrimary(@Qualifier("dataSourcePropertiesPrimary") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties dataSourcePropertiesSecondary() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSourceSecondary(@Qualifier("dataSourcePropertiesSecondary") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
