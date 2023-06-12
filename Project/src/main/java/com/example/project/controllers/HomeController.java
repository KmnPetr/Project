package com.example.project.controllers;

import com.example.project.models.Person;
import com.example.project.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    /**
     * выдает первую домашнюю страницу после успешной аутентификации
     */
    @GetMapping()
    public String homePage(Model model){
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();
        model.addAttribute("username",person.getUsername());

        return "home/home";
    }
}
