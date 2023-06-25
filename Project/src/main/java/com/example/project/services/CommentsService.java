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

    /**
     * метод реагирует на нажатие кнопок лайков и дизлайков
     */
    @Transactional
    public void updateLikes(String type, int id) {
        switch (type) {
            case "like":
                commentsRepository.updateCountLike((long) id);
                break;
            case "dislike":
                commentsRepository.updateCountDislike((long) id);
                break;
            default:
                System.out.println("Некорректный тип");
        }
    }
}
