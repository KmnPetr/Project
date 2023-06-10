package com.example.project.services;

import com.example.project.models.Person;
import com.example.project.repositories.PeopleRepositry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PeopleService {
    private final PeopleRepositry peopleRepositry;
    @Autowired
    public PeopleService(PeopleRepositry peopleRepositry) {
        this.peopleRepositry = peopleRepositry;
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


        peopleRepositry.save(person);
    }
}
