package com.example.project.repositories;

import com.example.project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Integer> {
    /**
     * изменяет количество лайков
     */
    @Modifying
    @Query("UPDATE Comment c SET c.count_likes = c.count_likes + 1 WHERE c.id = :id")
    void updateCountLike(@Param("id") Long id);

    /**
     * изменяет количество дизлайков
     */
    @Modifying
    @Query("UPDATE Comment c SET c.count_dislikes = c.count_dislikes + 1 WHERE c.id = :id")
    void updateCountDislike(@Param("id") Long id);
}
