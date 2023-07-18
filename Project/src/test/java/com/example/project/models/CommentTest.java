package com.example.project.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private Comment comment;
    private static LocalDate localDate;
    private static Person person;
    private static Person person2;

    @BeforeAll
    private static void prepareData2(){
        localDate=LocalDate.of(2022, 12, 31);
        person=new Person(1, "name of user", "some password", "ROLE", LocalDateTime.of(2023, 7, 15, 12, 30));
        person2=new Person(2, "another name of user", "another password", "ANOTHER_ROLE", LocalDateTime.now());
    }
    @BeforeEach
    private void prepareData(){
        comment=new Comment(1,"some text",localDate,person);
    }

    @Test
    public void testEmptyConstructor() {
        Comment emptyComment = new Comment();
        assertNotNull(emptyComment);
    }

    @Test
    void getId() {
        assertEquals(1,comment.getId());
    }

    @Test
    void setId() {
        comment.setId(10);
        assertEquals(10,comment.getId());
    }

    @Test
    void getText() {
        assertEquals("some text",comment.getText());
    }

    @Test
    void setText() {
        comment.setText("another text");
        assertEquals("another text",comment.getText());
    }

    @Test
    void getCreated_at() {
        assertEquals(localDate,comment.getCreated_at());
    }

    @Test
    void setCreated_at() {
        comment.setCreated_at(LocalDate.of(2030, 2, 1));
        assertEquals(LocalDate.of(2030, 2, 1),comment.getCreated_at());
    }

    @Test
    void getOwner() {
        assertEquals(person,comment.getOwner());
    }

    @Test
    void setOwner() {
        comment.setOwner(person2);
        assertEquals(person2,comment.getOwner());
    }

    @Test
    void ToString() {
        assertEquals("Comment{id=1, text='some text', created_at="+localDate+", owner=Person{id=1, username='name of user', password='some password', role='ROLE', createdAt=2023-07-15T12:30}}",
        comment.toString());
    }
}