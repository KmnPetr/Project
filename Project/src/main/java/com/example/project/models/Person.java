package com.example.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    @NotBlank(message = "This field should not be empty.")
    @Size(min = 1,max = 30,message = "This field must contain from 1 to 30 characters.")
    private String username;
    @Column(name = "password")
    @NotBlank(message = "This field should not be empty.")
    @Size(min = 1,max = 100,message = "This field must contain from 1 to 100 characters.")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
//    @OneToMany(mappedBy = "owner")
//    private List<Comment> comments;

    public Person() {}

    public Person(int id, String username, String password, String role, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
//    public List<Comment> getComments() {return comments;}
//    public void setComments(List<Comment> comments) {this.comments = comments;}

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(username, person.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
