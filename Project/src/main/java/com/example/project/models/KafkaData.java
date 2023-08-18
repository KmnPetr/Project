package com.example.project.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * дата класс для сохранения инфы в топике кафки и отправки из него на html страницу пользователя
 */
@Getter
@Setter
public class KafkaData {
    private String message;
    private Integer partition;
    private Long offset;
    private LocalDateTime createdAt;
}
