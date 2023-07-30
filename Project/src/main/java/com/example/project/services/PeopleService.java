package com.example.project.services;

import com.example.project.models.Person;
import com.example.project.repositories.PeopleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder encoder) {
        this.peopleRepository = peopleRepository;
        this.encoder = encoder;
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

    @Transactional
    public void registrGuest(HttpServletRequest request) {
        List<Person> guests = peopleRepository.findGuestsPersons();

        ArrayList<Integer> sortedFilteredGuests=guests.stream()
                .map(guest->{
                    try{
                        return Integer.valueOf(guest.getUsername().substring(6));
                    }catch (NumberFormatException e){
                        return -1; // данный элемент будет отфильтрован
                    }
                })
                .filter(number->number>=0)
                .sorted()
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        String guestName = null;
        
        for (int i = 0; i < sortedFilteredGuests.size(); i++) {
            if(i!=sortedFilteredGuests.get(i)){
                guestName="Guest_"+i;
                break;
            }
        }

        Person person=new Person();
        person.setUsername(guestName);
        person.setPassword(encoder.encode("0000"));//кодировать не буду нет смысла
        person.setRole("ROLE_GUEST");
        person.setCreatedAt(LocalDateTime.now());
        registr(person);//зарегестрировали

        try {
            request.login(person.getUsername(), "0000"/*здесь передается незакодированный пароль*/);//принудительно создаем сессию
        } catch (ServletException e) {
            System.out.println("Error while login "+e);
        }
    }
}
