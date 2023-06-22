package com.example.project.controllers;


import com.example.project.models.Comment;
import com.example.project.services.CommentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/comment")
public class CommentsController {
    private final CommentsService commentsService;
    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping()
    public String getAll(){
        return "home/commentPage";
    }
    @GetMapping("/get_list")
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("comment")Comment comment,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())return "home/commentPage";

        commentsService.create(comment);

        return ""
    }
}
