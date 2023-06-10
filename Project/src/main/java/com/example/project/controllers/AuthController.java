package com.example.project.controllers;

import com.example.project.models.Person;
import com.example.project.security.PersonDetails;
import com.example.project.services.PeopleService;
import com.example.project.security.PersonDetailsService;
import com.example.project.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(PersonValidator personValidator, PeopleService peopleService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * выдает страницу аутентификации
     */
    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    /**
     * выдает страницу регистрации нового пользователя
     */
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person peson/*полож.пустого Person в модель*/){
        return "auth/registration";
    }
    /**
     * принимает данные с формы registration
     */
    @PostMapping("/registration")
    public String performRegistration(@Valid @ModelAttribute("person")Person person/*получ.данных с формы*/,
                                      BindingResult bindingResult){
        personValidator.validate(person,bindingResult);

        if(bindingResult.hasErrors())return "auth/registration";


        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setCreatedAt(LocalDateTime.now());

        PersonDetails personDetails=new PersonDetails(person);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                personDetails,
                null,
                personDetails.getAuthorities());

        Authentication authenticated = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        peopleService.registr(person);

        return "auth/succesRegistPage";
    }
}
