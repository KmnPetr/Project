package com.example.project.services;

import com.example.project.models.Comment;
import com.example.project.models.Person;
import com.example.project.repositories.CommentsRepository;
import com.example.project.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        comment.setCreated_at(LocalDate.now());

        commentsRepository.save(comment);
    }

    public Comment getCommentById(int id) {
        Comment comment=commentsRepository.findById(id).orElse(null);
        if(comment==null)throw new IllegalArgumentException("comment with id "+id+" not found");
        return comment;
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
