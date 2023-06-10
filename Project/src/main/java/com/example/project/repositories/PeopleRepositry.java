package com.example.project.repositories;

import com.example.project.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepositry extends JpaRepository<Person,Integer> {
    Optional<Person>findByUsername(String username);
}
