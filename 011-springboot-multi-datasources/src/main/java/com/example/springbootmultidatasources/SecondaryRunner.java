package com.example.springbootmultidatasources;

import com.example.springbootmultidatasources.model.secondary.User;
import com.example.springbootmultidatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SecondaryRunner implements ApplicationRunner {

    @Autowired
    @Qualifier("userServiceMybatisImpl")
    UserService userServiceMybatisImpl;

    @Autowired
    @Qualifier("userServiceJpaImpl")
    UserService userServiceJpaImpl;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userServiceMybatisImpl.truncateTableUser();

        userServiceMybatisImpl.saveAll(
                IntStream
                        .range(1, 9)
                        .mapToObj(x -> {
                            return User.builder()
                                    .username(UUID.randomUUID().toString())
                                    .password(UUID.randomUUID().toString())
                                    .status(Math.random() > 0.5 ? "normal" : "error")
                                    .build();
                        })
                        .collect(Collectors.toList())
        );

        userServiceJpaImpl.findAll().stream().forEach(System.out::println);

        userServiceMybatisImpl.findUsersByStatus("error").forEach(System.out::println);

    }
}
