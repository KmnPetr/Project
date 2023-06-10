package com.example.project.controllers;

import com.example.project.models.Person;
import com.example.project.services.PeopleService;
import com.example.project.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    @Autowired
    public AuthController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
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
        System.out.println("performRegistration called");
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())return "auth/registration";

        peopleService.registr(person);

        return "auth/succesRegistPage";
    }
}
