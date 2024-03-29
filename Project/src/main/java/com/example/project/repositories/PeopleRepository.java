package com.example.project.repositories;

import com.example.project.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person>findByUsername(String username);

    @Query("SELECT p FROM Person p WHERE p.username LIKE 'Guest_%'")
    List<Person> findGuestsPersons();
}
