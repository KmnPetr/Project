package com.example.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    @NotBlank(message = "This field should not be empty.")
    @Max(value = 3000,message = "The maximum number of characters is 3000.")
    private String text;
    @Column(name = "count_likes")
    private int count_likes;
    @Column(name = "count_dislikes")
    private int count_dislikes;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "owner")
    private int owner;

    public Comment() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public int getCount_likes() {return count_likes;}
    public void setCount_likes(int count_likes) {this.count_likes = count_likes;}
    public int getCount_dislikes() {return count_dislikes;}
    public void setCount_dislikes(int count_dislikes) {this.count_dislikes = count_dislikes;}
    public Date getCreated_at() {return created_at;}
    public void setCreated_at(Date created_at) {this.created_at = created_at;}
    public int getOwner() {return owner;}
    public void setOwner(int owner) {this.owner = owner;}
}
