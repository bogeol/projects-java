package com.example.springbootmybatisvuejs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DefaultView implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // forward默认为static静态资源里面指向
        registry.addViewController("/").setViewName("forward:/html/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}