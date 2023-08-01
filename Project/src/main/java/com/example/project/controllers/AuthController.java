package com.example.project.controllers;

import com.example.project.aop.Visitor;
import com.example.project.models.Person;
import com.example.project.security.PersonDetails;
import com.example.project.services.PeopleService;
import com.example.project.util.PersonValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public AuthController(PersonValidator personValidator, PeopleService peopleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * выдает страницу аутентификации
     */
    @Visitor(value = "login page")
    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    /**
     * выдает страницу регистрации нового пользователя
     */
    @Visitor(value = "registration")
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person peson/*полож.пустого Person в модель*/){
        return "auth/registration";
    }
    /**
     * принимает данные с формы registration
     */
    @PostMapping("/registration")
    public String performRegistration(
            @Valid @ModelAttribute("person")Person person/*получ.данных с формы*/,
            BindingResult bindingResult,
            HttpServletRequest request){
        personValidator.validate(person,bindingResult);

        if(bindingResult.hasErrors())return "auth/registration";

        String noEncodePassword=person.getPassword();

        person.setPassword(passwordEncoder.encode(noEncodePassword));
        person.setRole("ROLE_USER");
        person.setCreatedAt(LocalDateTime.now());

        PersonDetails personDetails=new PersonDetails(person);

        peopleService.registr(person);//относим в БД

        try {
            request.login(personDetails.getUsername(), noEncodePassword);//принудительно создаем сессию
        } catch (ServletException e) {
            System.out.println("Error while login "+e);
        }

        return "redirect:/auth/succesRegistPage";
    }

    @Visitor(value = "succes regist page")
    @GetMapping("/succesRegistPage")
    public String succesRegistPage(){
        System.out.println("method auth/succesRegistPage called");
        return "auth/succesRegistPage";
    }

    @Visitor(value = "guest registration")
    @GetMapping("guest_registration")
    public String guestRegistrationAndGoHomePage(HttpServletRequest request)
    {
        peopleService.registrGuest(request);

        return "redirect:/home";
    }
}