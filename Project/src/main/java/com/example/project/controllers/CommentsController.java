package com.example.project.controllers;


import com.example.project.models.Comment;
import com.example.project.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentsController {
    private final CommentsService commentsService;
    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/get_all")
    public List<Comment> getAllCommentJSON(){
        System.out.println("getAllCommentJSON called");
        return commentsService.getAllComments();
    }
//    @GetMapping("/get_list")
//    @PostMapping("/create")
//    public String create(@Valid @ModelAttribute("comment")Comment comment,
//                         BindingResult bindingResult){
//        if(bindingResult.hasErrors())return "home/commentPage";
//
//        commentsService.create(comment);
//
//        return ""
//    }
}
