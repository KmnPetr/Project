package com.example.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CommentDTOTest {

    CommentDTO commentDTO;

    @BeforeEach
    void setUp() {
        commentDTO=new CommentDTO(
                3,
                "some text",
                LocalDate.of(2022,12,3),
                44,
                "username",
                "ROLE_USER",
                "like",
                234,
                54);
    }

    @Test
    void testEmptyConstructor(){
        CommentDTO commentDTO2=new CommentDTO();
        assertNotNull(commentDTO2);
    }

    @Test
    void getId() {
        assertEquals(3,commentDTO.getId());
    }

    @Test
    void setId() {
        commentDTO.setId(77);
        assertEquals(77,commentDTO.getId());
    }

    @Test
    void getText() {
        assertEquals("some text", commentDTO.getText());
    }

    @Test
    void setText() {
        commentDTO.setText("another text");
        assertEquals("another text",commentDTO.getText());
    }

    @Test
    void getCreated_at() {
        assertEquals(LocalDate.of(2022,12,3),commentDTO.getCreated_at());
    }

    @Test
    void setCreated_at() {
        commentDTO.setCreated_at(LocalDate.of(2055,2,16));
        assertEquals(LocalDate.of(2055,2,16),commentDTO.getCreated_at());
    }

    @Test
    void getOwner_id() {
        assertEquals(44,commentDTO.getOwner_id());
    }

    @Test
    void setOwner_id() {
        commentDTO.setOwner_id(55);
        assertEquals(55,commentDTO.getOwner_id());
    }

    @Test
    void getOwner_name() {
        assertEquals("username",commentDTO.getOwner_name());
    }

    @Test
    void setOwner_name() {
        commentDTO.setOwner_name("another username");
        assertEquals("another username",commentDTO.getOwner_name());
    }

    @Test
    void getOwner_role() {
        assertEquals("ROLE_USER",commentDTO.getOwner_role());
    }

    @Test
    void setOwner_role() {
        commentDTO.setOwner_role("ANOTHER_ROLE");
        assertEquals("ANOTHER_ROLE",commentDTO.getOwner_role());
    }

    @Test
    void getLike_status() {
        assertEquals("like",commentDTO.getLike_status());
    }

    @Test
    void setLike_status() {
        commentDTO.setLike_status("dislike");
        assertEquals("dislike",commentDTO.getLike_status());
    }

    @Test
    void getCount_likes() {
        assertEquals(234,commentDTO.getCount_likes());
    }

    @Test
    void setCount_likes() {
        commentDTO.setCount_likes(43);
        assertEquals(43,commentDTO.getCount_likes());
    }

    @Test
    void getCount_dislikes() {
        assertEquals(54,commentDTO.getCount_dislikes());
    }

    @Test
    void setCount_dislikes() {
        commentDTO.setCount_dislikes(33);
        assertEquals(33,commentDTO.getCount_dislikes());
    }

    @Test
    void testToString() {
        assertEquals("CommentDTO:\n" +
                "\tid=3\n" +
                "\ttext='some text'\n" +
                "\tcreated_at=2022-12-03\n" +
                "\towner_id=44\n" +
                "\towner_name='username'\n" +
                "\towner_role='ROLE_USER'\n" +
                "\tlikeStatus='like'\n" +
                "\tcount_likes=234\n" +
                "\tcount_dislikes=54",commentDTO.toString());
    }
}