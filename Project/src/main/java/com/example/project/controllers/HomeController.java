package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    /**
     * выдает первую домашнюю страницу после успешной аутентификации
     */
    @GetMapping()
    public String homePage(){
        return "home/home";
    }
}
