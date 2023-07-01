package com.example.project.repositories;

import com.example.project.models.LikeAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeAction, LikeAction.LikeActionId> {
}
