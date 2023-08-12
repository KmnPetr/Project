package com.example.project.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Установи размер базового пула потоков
        executor.setMaxPoolSize(10); // Установи максимальное число потоков
        executor.setQueueCapacity(25); // Установи максимальный размер очереди задач
        executor.setThreadNamePrefix("AsyncThread-"); // Префикс имени для потоков
        executor.initialize();
        return executor;
    }
}
