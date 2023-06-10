package com.example.project.services;

import com.example.project.models.Person;
import com.example.project.repositories.PeopleRepositry;
import com.example.project.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepositry peopleRepositry;
    @Autowired
    public PersonDetailsService(PeopleRepositry peopleRepositry) {
        this.peopleRepositry = peopleRepositry;
    }

    /**
     * этот метод Spring вызовет сам для поиска Person и его данных в БД
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called");
        Optional<Person>person=peopleRepositry.findByUsername(username);
        if(person.isEmpty())throw new UsernameNotFoundException("User not found!");
        return new PersonDetails(person.get());
    }
}
