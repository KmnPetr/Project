package com.example.project.controllers;


import com.example.project.dto.CommentDTO;
import com.example.project.models.Comment;
import com.example.project.models.LikeAction;
import com.example.project.models.Person;
import com.example.project.security.PersonDetails;
import com.example.project.services.CommentsService;
import com.example.project.services.LikesService;
import com.example.project.services.PeopleService;
import com.example.project.util.SomeEnams;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentsController {
    private final CommentsService commentsService;
    private final LikesService likesService;
    private final PeopleService peopleService;

    @Autowired
    public CommentsController(CommentsService commentsService, LikesService likesService, PeopleService peopleService) {
        this.commentsService = commentsService;
        this.likesService = likesService;
        this.peopleService = peopleService;
    }

    @GetMapping("/get_all")
    public List<CommentDTO> getAllCommentJSON(){
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();

        //чтобы hibernate не делал отдельных запросов, а подгрузил кэш сразу списком
        peopleService.findAll();

        List<Comment> comments=commentsService.getAllComments();
        List<LikeAction> likes=likesService.getAllLikes();

        List<CommentDTO> commentDTO = new ArrayList<>();

        for (Comment el:comments) {
            commentDTO.add(convertToCommentDTO(el,likes,person));
        }
        return commentDTO;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@Valid @RequestBody Comment comment) {

        commentsService.create(comment);

        return ResponseEntity.ok("Комментарий создан");
    }

    @GetMapping("/like")
    public CommentDTO Liked(@RequestParam("type")String type,@RequestParam("id")int id){
        //вносим изменения в БД
        likesService.saveLikeAction(id,type);

        //отдаем обновленный commentDTO
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();

        return convertToCommentDTO(
                commentsService.getCommentById(id),
                likesService.getAllLikes(),
                person
        );
    }

    private CommentDTO convertToCommentDTO(Comment comment,
                                           List<LikeAction>likes,
                                           Person person){
        String username;
        //TODO необходимо зарезервировать логин "Ваш комментарий:"
       if(comment.getOwner().getUsername().equals(person.getUsername()))username= SomeEnams.RESERVED_LOGIN.getValue();
       else username= person.getUsername();



        return new CommentDTO(
                comment.getId(),
                comment.getText(),
                comment.getCreated_at().toLocalDate(),
                comment.getOwner().getId(),
                username,
                comment.getOwner().getRole(),
                likes.stream()
                        .filter(it -> it.getId().getPersonId()== person.getId()&&it.getId().getCommentId()==comment.getId())
                        .findFirst().map(LikeAction::getType)
                        .orElse(null),
                (int)likes.stream().filter(it->
                        it.getId().getCommentId()==comment.getId()&&it.getType().equals(SomeEnams.LIKE.getValue())
                ).count(),
                (int)likes.stream().filter(it->
                        it.getId().getCommentId()==comment.getId()&&it.getType().equals(SomeEnams.DISLIKE.getValue())
                ).count()
        );
    }
}
