package com.example.project.repositories;

import com.example.project.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Visit v WHERE v.session = :session")
    void deleteAllBySession(String session);
}
