package com.example.project.services;

import com.example.project.models.Comment;
import com.example.project.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public List<Comment> getAllComments(){
        System.out.println("getAllComments called");
        return commentsRepository.findAll();
    }
}
