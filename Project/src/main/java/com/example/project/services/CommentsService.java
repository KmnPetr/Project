package com.example.project.services;

import com.example.project.models.Comment;
import com.example.project.models.Person;
import com.example.project.repositories.CommentsRepository;
import com.example.project.security.PersonDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final HttpServletRequest httpServletRequest;
    @Autowired
    public CommentsService(CommentsRepository commentsRepository, HttpServletRequest httpServletRequest) {
        this.commentsRepository = commentsRepository;
        this.httpServletRequest = httpServletRequest;
    }

    public List<Comment> getAllComments(){
        return commentsRepository.findAll();
    }
    @Transactional
    public void create(Comment comment){

        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();

        comment.setOwner(person);
        comment.setCreated_at(LocalDate.now());

        commentsRepository.save(comment);
    }

    public Comment getCommentById(int id) {
        Comment comment=commentsRepository.findById(id).orElse(null);
        if(comment==null)throw new IllegalArgumentException("comment with id "+id+" not found");
        return comment;
    }

    @Transactional
    public void update(Comment comment) {
        System.out.println("проверка владельца");
        Comment checkComment=commentsRepository.findById(comment.getId()).orElse(null);
        Principal principal = httpServletRequest.getUserPrincipal();
        if (checkComment!=null && checkComment.getOwner().getUsername().equals(principal.getName())){
            System.out.println("обновление");
            commentsRepository.updateCommentById(comment.getText(),comment.getId());
        }
    }

    @Transactional
    public void delete(int id) {
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();
        Comment comment=commentsRepository.findById(id).orElse(null);
        if(comment!=null&&person.getId()==comment.getOwner().getId())
            commentsRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Попытка удалить чужой или несуществующий коммент");
    }

}
