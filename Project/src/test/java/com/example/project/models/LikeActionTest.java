package com.example.project.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeActionTest {

    LikeAction likeAction;
    LikeActionId likeActionId;

    @BeforeEach
    void setUp() {
        likeAction=new LikeAction(4,56,"like");
        likeActionId=new LikeActionId(4,56);

    }

    @Test
    void testEmptyConstructor(){
        LikeAction likeAction1=new LikeAction();
        assertNotNull(likeAction1);
    }

    @Test
    void getId() {
        assertEquals(likeActionId,likeAction.getId());
    }

    @Test
    void setId() {
        likeAction.setId(new LikeActionId(8,67));
        assertEquals(new LikeActionId(8,67),likeAction.getId());
    }

    @Test
    void getType() {
        assertEquals("like",likeAction.getType());
    }

    @Test
    void setType() {
        likeAction.setType("dislike");
        assertEquals("dislike",likeAction.getType());
    }

    @Test
    void testToString() {
        assertEquals("LikeAction:\n" +
                "\tid=LikeActionId:\n" +
                "\tpersonId=4\n" +
                "\tcommentId=56\n" +
                "\ttype='like'",likeAction.toString());
    }
}