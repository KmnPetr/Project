package com.example.project.util;

public enum SomeEnams {
    LIKE("like"),
    DISLIKE("dislike"),
    RESERVED_LOGIN("Ваш комментарий:");
    private final String value;
    SomeEnams(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
