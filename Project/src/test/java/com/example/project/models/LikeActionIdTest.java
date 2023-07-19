package com.example.project.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LikeActionIdTest {

    LikeActionId likeActionId;

    @BeforeEach
    void setUp() {
        likeActionId=new LikeActionId(56,77);
    }

    @Test
    void testEmptyConstructor(){
        LikeActionId likeActionId=new LikeActionId();
        assertNotNull(likeActionId);
    }

    @Test
    void getPersonId() {
        assertEquals(56,likeActionId.getPersonId());
    }

    @Test
    void setPersonId() {
        likeActionId.setPersonId(33);
        assertEquals(33,likeActionId.getPersonId());
    }

    @Test
    void getCommentId() {
        assertEquals(77,likeActionId.getCommentId());
    }

    @Test
    void setCommentId() {
        likeActionId.setCommentId(44);
        assertEquals(44,likeActionId.getCommentId());
    }

    @ParameterizedTest
    @CsvSource({
            "56,77,true",
            "56,4,false",
            "34,77,false"
    })
    void testEquals(int perId,int commId,boolean expected) {
        LikeActionId likeActionId2=new LikeActionId(perId,commId);
        assertEquals(expected,likeActionId.equals(likeActionId2));
    }

    @Test
    void testEquals2(){
        LikeActionId likeActionId3=new LikeActionId(56,null);
        assertEquals(false,likeActionId.equals(likeActionId3));

        LikeActionId likeActionId4=new LikeActionId(null,77);
        assertEquals(false,likeActionId.equals(likeActionId4));
    }

    @Test
    void testHashCode() {
        LikeActionId likeActionId2=new LikeActionId(56,77);
        LikeActionId likeActionId3=new LikeActionId(23,888);
        assertEquals(likeActionId.hashCode(),likeActionId2.hashCode());
        assertNotEquals(likeActionId.hashCode(),likeActionId3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("LikeActionId:\n" +
                "\tpersonId=56\n" +
                "\tcommentId=77",likeActionId.toString());
    }
}