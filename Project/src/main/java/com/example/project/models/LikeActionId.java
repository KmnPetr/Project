package com.example.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LikeActionId implements Serializable {

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "comment_id")
    private Integer commentId;

    public LikeActionId() {
    }

    public LikeActionId(Integer personId, Integer commentId) {
        this.personId = personId;
        this.commentId = commentId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        LikeActionId lai = (LikeActionId) o;
        return personId.equals(lai.personId) && commentId.equals(lai.commentId);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(personId, commentId);
    }

    @Override
    public String toString() {
        return "LikeActionId:" +
                "\n\tpersonId=" + personId +
                "\n\tcommentId=" + commentId;
    }
}
