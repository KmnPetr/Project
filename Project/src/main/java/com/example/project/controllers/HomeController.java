package com.example.project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final HttpServletRequest httpServletRequest;
    @Autowired
    public HomeController(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * выдает первую домашнюю страницу после успешной аутентификации
     */
    @GetMapping()
    public String homePage(Model model){
        Principal principal = httpServletRequest.getUserPrincipal();
        model.addAttribute("username",principal.getName());

//        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("username",personDetails.getUsername());

        return "home/home";
    }

    /**
     * выдает страницу для просмотра комментариев
     */
    @GetMapping("/comment")
    public String getCommentPage(){
        return "home/comment";
    }
}
