package com.example.project.services;

import com.example.project.models.Person;
import com.example.project.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    /**
     * найдет Person по username или вернет null
     */
    public Person findByUsername(String username) {
        return peopleRepository.findByUsername(username).orElse(null);
    }
    /**
     * зарегестрирует нового человека
     */
    @Transactional
    public void registr(Person person){
        peopleRepository.save(person);
    }

}
