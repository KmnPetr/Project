package com.example.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    @NotBlank(message = "This field should not be empty.")
    @Size(min = 0,max = 3000,message = "The maximum number of characters is 3000.")
    private String text;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private Person owner;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public LocalDateTime getCreated_at() {return created_at;}
    public void setCreated_at(LocalDateTime created_at) {this.created_at = created_at;}
    public Person getOwner() {return owner;}
    public void setOwner(Person owner) {this.owner = owner;}

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", created_at=" + created_at +
                ", owner=" + owner +
                '}';
    }
}
