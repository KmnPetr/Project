package com.example.project.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private int id;
    private String text;
    private Integer count_likes;
    private Integer count_dislikes;
    private LocalDateTime created_at;
    private Integer owner_id;
    private String owner_name;
    private String owner_role;
    public CommentDTO() {}
    public CommentDTO(int id, String text, Integer count_likes, Integer count_dislikes, LocalDateTime created_at, Integer owner_id, String owner_name, String owner_role) {
        this.id = id;
        this.text = text;
        this.count_likes = count_likes;
        this.count_dislikes = count_dislikes;
        this.created_at = created_at;
        this.owner_id = owner_id;
        this.owner_name = owner_name;
        this.owner_role = owner_role;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Integer getCount_likes() {return count_likes;}
    public void setCount_likes(Integer count_likes) {this.count_likes = count_likes;}
    public Integer getCount_dislikes() {return count_dislikes;}
    public void setCount_dislikes(Integer count_dislikes) {this.count_dislikes = count_dislikes;}
    public LocalDateTime getCreated_at() {return created_at;}
    public void setCreated_at(LocalDateTime created_at) {this.created_at = created_at;}
    public Integer getOwner_id() {return owner_id;}
    public void setOwner_id(Integer owner_id) {this.owner_id = owner_id;}
    public String getOwner_name() {return owner_name;}
    public void setOwner_name(String owner_name) {this.owner_name = owner_name;}
    public String getOwner_role() {return owner_role;}
    public void setOwner_role(String owner_role) {this.owner_role = owner_role;}
    @Override
    public String toString() {
        return "CommentDTO:" + "\n\tid=" + id + "\n\ttext='" + text + '\'' + "\n\tcount_likes=" + count_likes + "\n\tcount_dislikes=" + count_dislikes + "\n\tcreated_at=" + created_at + "\n\towner_id=" + owner_id + "\n\towner_name='" + owner_name + '\'' + "\n\towner_role='" + owner_role + '\'';
    }
}
