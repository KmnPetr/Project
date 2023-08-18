package com.example.project.kafka.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

/**
 * данный класс обрабатывает подключения/отключения/обработка отправки/приема сообщений сессий
 * в реалиях этого класса, он добавляет/удаляет сессии в/из пулла сессий,
 * с целью возможности доступа к ним из других сервисов
 * в данном случае для доступа к ним из класса Consumer
 */
@Component
public class SocketHandler implements WebSocketHandler {

    private SessionPool sessionPool;
    @Autowired
    public SocketHandler(SessionPool sessionPool) {
        this.sessionPool = sessionPool;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        System.out.println("WebSocket соединение установлено: " + session.getId());

        sessionPool.addSession(session);
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {}

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception){}

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        System.out.println("WebSocket соединение закрыто: " + session.getId());

        sessionPool.removeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}