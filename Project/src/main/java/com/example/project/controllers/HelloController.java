package com.example.project.controllers;

import com.example.project.aop.Visitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Visitor(value = "Hello")
    @GetMapping("/hello")
    public String helloPage(){
        return "hello";
    }
}