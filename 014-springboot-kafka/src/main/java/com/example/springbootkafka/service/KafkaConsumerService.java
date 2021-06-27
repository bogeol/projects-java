package com.example.springbootkafka.service;

import com.example.springbootkafka.model.PageView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(String msg) {
        PageView pageView = null;
        try {
            pageView = objectMapper.readValue(msg, PageView.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(String.format(
                "[ consumer ] => [ pageview id : %s ]",
                pageView.getId()
        ));

        /**
         * kafka：【也就是说topic作为数据中转站，可以无限中转，就是官网上提到的**event streaming**】
         *
         * (p -> topic1 -> c) => (p -> topic2 -> c) => ...
         *
         */
        //--------------------------resolve msg
        pageView.setFirstName(pageView.getFirstName().toUpperCase(Locale.ROOT));
        pageView.setLastName(pageView.getLastName().toUpperCase(Locale.ROOT));
        kafkaProducerService.sendMsgForPageView("topic-pageview-resolved", pageView);
        //--------------------------resolve msg


    }

}
