package com.example.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "kafka_data")
@Getter
@Setter
public class KafkaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "partition")
    private Integer partition;
    @Column(name = "kafka_offset")
    private Long offset;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "KafkaData:" +
                "\n\tid= " + id +
                "\n\tmessage= " + message +
                "\n\tpartition= " + partition +
                "\n\toffset= " + offset +
                "\n\tcreatedAt= " + createdAt;
    }
}
