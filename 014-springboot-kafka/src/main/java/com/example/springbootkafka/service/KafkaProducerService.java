package com.example.springbootkafka.service;

import com.example.springbootkafka.model.PageView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendMsgForPageView(String topic, PageView pageView) {

        ProducerRecord producerRecord = null;
        try {
            producerRecord = new ProducerRecord(topic, objectMapper.writeValueAsString(pageView)); // 配置中key/value全是String序列化【也可以配置为JSON序列化】
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("onFailure");
            }

            @Override
            public void onSuccess(SendResult<String, Object> sendResult) {
                logger.info(
                        String.format(
                                "[ producer (%s) ] => [ topic : %s ] - [ partition : %s ] - [ offset : %s ]",
                                Math.random() > 0.5 ? "  pc  " : "mobile", // 模拟多个producer生产数据，实际上这里是一个producer
                                sendResult.getRecordMetadata().topic(),
                                sendResult.getRecordMetadata().partition(),
                                sendResult.getRecordMetadata().offset()
                        ));
            }
        });
    }


}
