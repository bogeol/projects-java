package com.example.springbootkafka;

import com.example.springbootkafka.model.PageView;
import com.example.springbootkafka.service.KafkaProducerService;
import com.example.springbootkafka.service.MockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    MockDataService mockDataService;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String topic = "topic-pageview";

        while (true) {
            PageView pageView = mockDataService.mockDataPageView();
            kafkaProducerService.sendMsgForPageView(topic, pageView);
            Thread.sleep(1000);
        }
    }
}
