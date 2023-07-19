package com.example.project.dto;

import java.time.LocalDate;

public class CommentDTO {
    private int id;
    private String text;
    private LocalDate created_at;
    private Integer owner_id;
    private String owner_name;
    private String owner_role;

    //нужен для отображения конкретному пользователю лайкал ли он ранее конкретный комент("like","dislike",null)
    private String like_status;
    private Integer count_likes;
    private Integer count_dislikes;
    public CommentDTO() {}

    public CommentDTO(int id,
                      String text,
                      LocalDate created_at,
                      Integer owner_id,
                      String owner_name,
                      String owner_role,
                      String like_status,
                      int count_likes,
                      int count_dislikes) {
        this.id = id;
        this.text = text;
        this.created_at = created_at;
        this.owner_id = owner_id;
        this.owner_name = owner_name;
        this.owner_role = owner_role;
        this.like_status = like_status;
        this.count_likes = count_likes;
        this.count_dislikes = count_dislikes;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public LocalDate getCreated_at() {return created_at;}
    public void setCreated_at(LocalDate created_at) {this.created_at = created_at;}
    public Integer getOwner_id() {return owner_id;}
    public void setOwner_id(Integer owner_id) {this.owner_id = owner_id;}
    public String getOwner_name() {return owner_name;}
    public void setOwner_name(String owner_name) {this.owner_name = owner_name;}
    public String getOwner_role() {return owner_role;}
    public void setOwner_role(String owner_role) {this.owner_role = owner_role;}
    public String getLike_status() {return like_status;}
    public void setLike_status(String like_status) {this.like_status = like_status;}
    public Integer getCount_likes() {return count_likes;}
    public void setCount_likes(Integer count_likes) {this.count_likes = count_likes;}
    public Integer getCount_dislikes() {return count_dislikes;}
    public void setCount_dislikes(Integer count_dislikes) {this.count_dislikes = count_dislikes;}

    @Override
    public String toString() {
        return "CommentDTO:" +
                "\n\tid=" + id +
                "\n\ttext='" + text + '\'' +
                "\n\tcreated_at=" + created_at +
                "\n\towner_id=" + owner_id +
                "\n\towner_name='" + owner_name + '\'' +
                "\n\towner_role='" + owner_role + '\'' +
                "\n\tlikeStatus='" + like_status + '\'' +
                "\n\tcount_likes=" + count_likes +
                "\n\tcount_dislikes=" + count_dislikes;
    }
}
