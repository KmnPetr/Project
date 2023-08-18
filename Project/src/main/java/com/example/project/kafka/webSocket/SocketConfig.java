package com.example.project.kafka.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * конфигурация сокет соединения для страницы html - testKafka
 */
@Component
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {
    private SocketHandler socketHandler;
    @Autowired
    public SocketConfig(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/websocket").setAllowedOrigins("*");//TODO
    }
}
