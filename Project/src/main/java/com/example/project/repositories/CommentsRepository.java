package com.example.project.repositories;

import com.example.project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Comment SET text = :updatedText WHERE id = :id")
    void updateCommentById(@Param("updatedText") String updatedText, @Param("id")Integer id);
}
