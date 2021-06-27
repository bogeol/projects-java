package com.example.springbootmultidatasources;

import com.example.springbootmultidatasources.model.primary.Model;
import com.example.springbootmultidatasources.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PrimaryRunner implements ApplicationRunner {

    @Autowired
    @Qualifier("modelServiceJpaImpl")
    ModelService modelServiceJpaImpl;

    @Autowired
    @Qualifier("modelServiceJdbcImpl")
    ModelService modelServiceJdbcImpl;

    @Autowired
    @Qualifier("modelServiceJdbcTemplateImpl")
    ModelService modelServiceJdbcTemplateImpl;

    @Autowired
    @Qualifier("modelServiceMybatisImpl")
    ModelService modelServiceMybatisImpl;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        modelServiceMybatisImpl.truncateTableModel();

        modelServiceJpaImpl.findAll().stream().forEach(System.out::println);

        List<Model> modelList = IntStream
                .range(1, 5)
                .mapToObj(x -> {
                    return Model.builder()
                            .modelName(UUID.randomUUID().toString())
                            .modelDescription(UUID.randomUUID().toString())
                            .modelStatus(Math.random() > 0.5 ? "normal" : "error")
                            .build();
                })
                .collect(Collectors.toList());
        modelServiceJpaImpl.saveAll(modelList);

        modelServiceJpaImpl.findAll().stream().forEach(System.out::println);

        System.out.println(modelServiceJdbcImpl.version());

        System.out.println(modelServiceJdbcTemplateImpl.version());

    }
}
