package com.example.springbootmultidatasources.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.springbootmultidatasources.dao.primary.repository",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanPrimary",
        transactionManagerRef = "platformTransactionManagerPrimary"
)
@EntityScan(basePackages = "com.example.springbootmultidatasources.model.primary")
@EnableTransactionManagement
public class JpaConfigurationPrimary {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanPrimary(
            @Qualifier("dataSourcePrimary") DataSource dataSource,
            EntityManagerFactoryBuilder entityManagerFactoryBuilder
    ) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.example.springbootmultidatasources.model.primary")
                .persistenceUnit("dataSourcePrimary")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager platformTransactionManagerPrimary(
            @Qualifier("localContainerEntityManagerFactoryBeanPrimary") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
