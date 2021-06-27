package com.example.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    final String TOPIC1 = "topic-pageview";
    final String TOPIC2 = "topic-pageview-resolved";

    @Bean
    public NewTopic newTopicPageView() {
        return TopicBuilder
                .name(TOPIC1)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newTopicPageViewResolved() {
        return TopicBuilder
                .name(TOPIC2)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
