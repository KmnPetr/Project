package com.example.project.services;

import com.example.project.models.Person;
import com.example.project.repositories.PeopleRepositry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PeopleService {
    private final PeopleRepositry peopleRepositry;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public PeopleService(PeopleRepositry peopleRepositry, PasswordEncoder passwordEncoder) {
        this.peopleRepositry = peopleRepositry;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * найдет Person по username или вернет null
     */
    public Person findByUsername(String username) {
        return peopleRepositry.findByUsername(username).orElse(null);
    }
    /**
     * зарегестрирует нового человека
     */
    @Transactional
    public void registr(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setCreatedAt(LocalDateTime.now());

        peopleRepositry.save(person);
    }
}
