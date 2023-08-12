package com.example.project.repositories;

import com.example.project.models.KafkaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaDataRepository extends JpaRepository<KafkaData,Long> {
}
