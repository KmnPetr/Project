package com.example.project.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * данный код является частью логики фиксации посещения пользователями определенных страниц приложения
 * данные приходят с методов контроллеров помеченных аннотацией @Visitor
 */
@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "session")
    private String session;
    @Column(name = "username")
    private String username;
    @Column(name = "value")
    private String value;
    @Column(name = "time")
    private LocalDateTime time;

    public Visit() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getSession() {return session;}
    public void setSession(String session) {this.session = session;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}
    public LocalDateTime getTime() {return time;}
    public void setTime(LocalDateTime time) {this.time = time;}
}
