package com.example.project.security;

import com.example.project.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PersonDetailsService implements UserDetailsService {
    public final PeopleService peopleService;
    @Autowired
    public PersonDetailsService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * этот метод Spring вызовет сам для поиска Person и его данных в БД
     */
    @Override
    public PersonDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called");
        PersonDetails personDetails=new PersonDetails(peopleService.findByUsername(username));
        if(personDetails.getPerson()==null)throw new UsernameNotFoundException("User not found!");
        return new PersonDetails(personDetails.getPerson());
    }
}
