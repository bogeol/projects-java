package com.example.springbootmybatisvuejs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.springbootmybatisvuejs.mapper")
public class SpringbootMybatisVuejsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisVuejsApplication.class, args);
    }

}
