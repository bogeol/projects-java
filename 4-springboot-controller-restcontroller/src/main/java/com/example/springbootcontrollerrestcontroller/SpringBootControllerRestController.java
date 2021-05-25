package com.example.springbootcontrollerrestcontroller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootControllerRestController {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootControllerRestController.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            System.out.println("####################################");
            String[] beans = applicationContext.getBeanDefinitionNames();
            Arrays.sort(beans);
            Arrays.stream(beans).forEach(x -> System.out.println(x));
            System.out.println("####################################");
        };
    }

}
