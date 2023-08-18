package com.example.project.kafka.webSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * класс содержит список web-socket сессий,
 * подключенных к приложению через html-страницу testKafka
 */
@Component
public class SessionPool {
    private static List<WebSocketSession> sessions = new ArrayList<>();


    public synchronized void addSession(WebSocketSession session){
        sessions.add(session);
        System.out.println("Соединение добавлено в список");
    }
    public synchronized void removeSession(WebSocketSession session){
        sessions.remove(session);
        System.out.println("Соединение удалено из списка");
    }
    public synchronized void sendMessages(String message){
        WebSocketMessage<String>socketMessage = new TextMessage(message);
        sessions.forEach(it-> {
            try {
                it.sendMessage(socketMessage);
            } catch (IOException e) {
                e.getMessage();
                e.printStackTrace();
            }
        });
    }
}