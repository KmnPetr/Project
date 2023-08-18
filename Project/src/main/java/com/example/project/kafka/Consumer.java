package com.example.project.kafka;

import com.example.project.kafka.webSocket.SessionPool;
import com.example.project.models.KafkaData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * это класс консьюмера,
 * он читает сообщения, посылаемые продюссером в кафку
 * сам продюссер находится в соседнем классе в этой же папке:)
 */
@Service
public class Consumer {

    private final SessionPool sessionPool;
    private final ObjectMapper objectMapper;
    @Autowired
    public Consumer(SessionPool sessionPool, ObjectMapper objectMapper) {
        this.sessionPool = sessionPool;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "demo_topic", groupId = "group")
    public void consume(ConsumerRecord<String, KafkaData> record) {

        KafkaData kafkaData = record.value();
        kafkaData.setPartition(record.partition());
        kafkaData.setOffset(record.offset());
        kafkaData.setCreatedAt(LocalDateTime.now());

        try {
            sessionPool.sendMessages(objectMapper.writeValueAsString(kafkaData));
        }catch (JsonProcessingException e){e.getMessage();e.printStackTrace();}
    }
}