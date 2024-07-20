package com.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic studentTopic() {
        return TopicBuilder.name("student-topic")
                .partitions(1)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic examTopic() {
        return TopicBuilder.name("exam-topic")
                .partitions(1)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic subjectTopic() {
        return TopicBuilder.name("subject-topic")
                .partitions(1)
                .replicas(3)
                .build();
    }

}