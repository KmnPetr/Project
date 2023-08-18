package com.example.project.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * конфигурация ассинхронных потоков
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Установка размера базового пула потоков
        executor.setMaxPoolSize(10); // Установка максимального число потоков
        executor.setQueueCapacity(25); // Установка максимального размер очереди задач
        executor.setThreadNamePrefix("AsyncThread-"); // Префикс имени для потоков
        executor.initialize();
        return executor;
    }
}
