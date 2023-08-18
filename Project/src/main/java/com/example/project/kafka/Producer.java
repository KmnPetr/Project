package com.example.project.kafka;

import com.example.project.models.KafkaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * это класс продюссера, который пишет сообщения в кафку.
 * Мне было лень выносить продюссер в отдельную ноду,
 * поэтому продюссер и консьюмер находяться в одной монолитной ноде
 */
@Service
public class Producer {
    private KafkaTemplate<String,KafkaData> kafkaTemplate;
    @Autowired
    public Producer(KafkaTemplate<String, KafkaData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendMessagesToKafka() {

        String key;
        String message;
        KafkaData kafkaData = new KafkaData();

        for (int i = 0; true; i++) {

            key= String.valueOf(i);

            if (new Random().nextInt(30)!=0){
                message = "Some producer sends the "+i+"-th message to kafka..";
            }else message = "Why does it doing it?";

            kafkaData.setMessage(message);
            kafkaData.setCreatedAt(LocalDateTime.now());

            kafkaTemplate.send("demo_topic",key,kafkaData);

            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){System.out.println(e.getMessage());}
        }
    }
}
