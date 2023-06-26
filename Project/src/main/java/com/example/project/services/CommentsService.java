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
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();

        switch (type) {
            case "like":
                commentsRepository.findById(id).ifPresent(it->{
                    if (it.getPeopleHaveDisliked().contains(person)){
                        //удалим запись, если person раньше дизлайкал запись
                        it.getPeopleHaveDisliked().remove(person);
                    }
                    if (it.getPeopleHaveLiked().contains(person)) {
                        //удалим запись, если person раньше лайкал запись
                        it.getPeopleHaveLiked().remove(person);
                    }else {
                        //создадим лайк если все ок
                        it.getPeopleHaveLiked().add(person);
                    }
                });
                break;
            case "dislike":
                commentsRepository.findById(id).ifPresent(it->{
                    if (it.getPeopleHaveLiked().contains(person)){
                        //удалим запись, если person раньше лайкал запись
                        it.getPeopleHaveLiked().remove(person);
                    }
                    if (it.getPeopleHaveDisliked().contains(person)) {
                        //удалим запись, если person раньше дизлайкал запись
                        it.getPeopleHaveDisliked().remove(person);
                    }else {
                        //создадим дизлайк если все ок
                        it.getPeopleHaveDisliked().add(person);
                    }
                });

                break;
            default:
                System.out.println("Некорректный тип");
        }
    }
}
