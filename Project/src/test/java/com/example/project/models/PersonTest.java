package com.example.project.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person;
    Person person2;
    Person person3;

    @BeforeEach
    public void prepareData(){
        person=new Person(
                1,
                "name of user",
                "some password",
                "ROLE",
                LocalDateTime.of(2023, 7, 15, 12, 30)
        );
        person2=new Person(
                1,
                "name of user",
                "some password",
                "ROLE",
                LocalDateTime.of(2023, 7, 15, 12, 30)
        );
        person3=new Person(
                5,
                "another name of user",
                "another password",
                "ANOTHER_ROLE",
                LocalDateTime.now()
        );
    }

    @Test
    public void testEmptyConstructor() {
        Person emptyPerson = new Person();
        assertNotNull(emptyPerson);
    }

    @Test
    void getId() {
        assertEquals(1,person.getId());
    }

    @Test
    void setId() {
        person.setId(4);
        assertEquals(4,person.getId());
    }

    @Test
    void getUsername() {
        assertEquals("name of user",person.getUsername());
    }

    @Test
    void setUsername() {
        person.setUsername("another name of user");
        assertEquals("another name of user",person.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("some password",person.getPassword());
    }

    @Test
    void setPassword() {
        person.setPassword("another password");
        assertEquals("another password",person.getPassword());
    }

    @Test
    void getRole() {
        assertEquals("ROLE",person.getRole());
    }

    @Test
    void setRole() {
        person.setRole("ANOTHER_ROLE");
        assertEquals("ANOTHER_ROLE",person.getRole());
    }

    @Test
    void getCreatedAt() {
        assertEquals(LocalDateTime.of(2023, 7, 15, 12, 30),person.getCreatedAt());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime ldt=LocalDateTime.now();
        person.setCreatedAt(ldt);
        assertEquals(ldt,person.getCreatedAt());
    }

    @Test
    void ToString() {
        assertEquals(
                "Person{id=1, username='name of user', password='some password', role='ROLE', createdAt=2023-07-15T12:30}",
                person.toString()
        );
    }

    @Test
    void Equals() {
        assertEquals(true,person.equals(person2));
        assertEquals(false,person.equals(person3));
    }

    @Test
    void HashCode() {
        assertEquals(person.hashCode(),person2.hashCode());
        assertNotEquals(person.hashCode(),person3.hashCode());
    }

}