package com.example.project.services;

import com.example.project.models.Comment;
import com.example.project.models.Person;
import com.example.project.repositories.CommentsRepository;
import com.example.project.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentsService {
    private final CommentsRepository commentsRepository;
    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public List<Comment> getAllComments(){
        return commentsRepository.findAll();
    }
    @Transactional
    public void create(Comment comment){

        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();

        comment.setOwner(person);
        comment.setCreated_at(LocalDateTime.now());
        comment.setCount_likes(0);
        comment.setCount_dislikes(0);

        System.out.println("пришли данные "+comment);

        commentsRepository.save(comment);
    }

}
