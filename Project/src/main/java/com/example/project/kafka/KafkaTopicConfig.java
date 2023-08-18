package com.example.project.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * при старте приложения, этот конфиг создаст необходимый топик
 * его не прийдется настраивать вручную
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("demo_topic")
                .build();
    }
}
