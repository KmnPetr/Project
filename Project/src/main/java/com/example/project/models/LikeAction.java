package com.example.project.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

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
    @Column(name = "action")
    private String action;

    public LikeAction() {}

    public LikeAction(Integer personId, Integer commentId, String action) {
        this.action = action;
        this.id=new LikeActionId(personId,commentId);
    }

    public LikeActionId getId() {return id;}
    public void setId(LikeActionId id) {this.id = id;}
//    public Person getPerson() {return person;}
//    public void setPerson(Person person) {this.person = person;}
//    public Comment getComment() {return comment;}
//    public void setComment(Comment comment) {this.comment = comment;}
    public String getAction() {return action;}
    public void setAction(String action) {this.action = action;}

    @Override
    public String toString() {
        return "LikeAction:" +
                "\n\tid=" + id +/*
                "\n\tperson=" + person +
                "\n\tcomment=" + comment +*/
                "\n\taction='" + action + '\'';
    }

    @Embeddable
    public static class LikeActionId implements Serializable {

        @Column(name = "person_id")
        private Integer personId;

        @Column(name = "comment_id")
        private Integer commentId;

        public LikeActionId() {}

        public LikeActionId(Integer personId, Integer commentId) {
            this.personId = personId;
            this.commentId = commentId;
        }

        public Integer getPersonId() {return personId;}
        public void setPersonId(Integer personId) {this.personId = personId;}
        public Integer getCommentId() {return commentId;}
        public void setCommentId(Integer commentId) {this.commentId = commentId;}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            LikeActionId lai = (LikeActionId) o;
            return personId.equals(lai.personId) && commentId.equals(lai.commentId);
        }
        @Override
        public int hashCode() {
            return 31*Objects.hash(personId, commentId);
        }

        @Override
        public String toString() {
            return "LikeActionId:" +
                    "\n\tpersonId=" + personId +
                    "\n\tcommentId=" + commentId;
        }
    }
}
