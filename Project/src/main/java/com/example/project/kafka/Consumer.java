package com.example.project.kafka;

import com.example.project.models.KafkaData;
import com.example.project.repositories.KafkaDataRepository;
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

    private final KafkaDataRepository kafkaDataRepository;
    @Autowired
    public Consumer(KafkaDataRepository kafkaDataRepository) {
        this.kafkaDataRepository = kafkaDataRepository;
    }

    @KafkaListener(topics = "demo_topic", groupId = "group")
    public void consume(ConsumerRecord<String, KafkaData> record) {

        KafkaData kafkaData = record.value();
        kafkaData.setPartition(record.partition());
        kafkaData.setOffset(record.offset());
        kafkaData.setCreatedAt(LocalDateTime.now());

        kafkaDataRepository.save(kafkaData);
    }
}