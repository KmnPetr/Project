package com.example.project.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Like_Actions_Person_Comment")
public class LikeAction {
    @EmbeddedId
    private LikeActionId id;
//    @ManyToOne
//    @MapsId("personId")//указывает на поле класса составного ключа
//    @JoinColumn(name = "person_id")
//    private Person person;
//    @ManyToOne
//    @MapsId("commentId")//указывает на поле класса составного ключа
//    @JoinColumn(name = "comment_id")
//    private Comment comment;
    @Column(name = "type")
    private String type;

    public LikeAction() {}

    public LikeAction(Integer personId, Integer commentId, String type) {
        this.type = type;
        this.id= new LikeActionId(personId, commentId);
    }

    public LikeActionId getId() {return id;}
    public void setId(LikeActionId id) {this.id = id;}
//    public Person getPerson() {return person;}
//    public void setPerson(Person person) {this.person = person;}
//    public Comment getComment() {return comment;}
//    public void setComment(Comment comment) {this.comment = comment;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    @Override
    public String toString() {
        return "LikeAction:" +
                "\n\tid=" + id +/*
                "\n\tperson=" + person +
                "\n\tcomment=" + comment +*/
                "\n\ttype='" + type + '\'';
    }

}

