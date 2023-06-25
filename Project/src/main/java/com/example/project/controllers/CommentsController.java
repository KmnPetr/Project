package com.example.project.controllers;


import com.example.project.dto.CommentDTO;
import com.example.project.models.Comment;
import com.example.project.services.CommentsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentsController {
    private final CommentsService commentsService;
    private final ModelMapper modelMapper;
    @Autowired
    public CommentsController(CommentsService commentsService, ModelMapper modelMapper) {
        this.commentsService = commentsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get_all")
    public List<CommentDTO> getAllCommentJSON(){
        List<Comment>comments=commentsService.getAllComments();
        List<CommentDTO>commentDTO = new ArrayList<>();
        for (Comment el:comments) {
            commentDTO.add(convertToCommentDTO(el));
        }
        return commentDTO;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@Valid @RequestBody Comment comment) {

        commentsService.create(comment);

        return ResponseEntity.ok("Комментарий создан");
    }

    @GetMapping("/like")
    public void Liked(@RequestParam("type")String type,@RequestParam("id")int id){
        System.out.println("Пользователь поставил "+type+" к "+id+" коментарию.");

        commentsService.updateLikes(type,id);
    }

    private CommentDTO convertToCommentDTO(Comment comment){
        return new CommentDTO(
                comment.getId(),
                comment.getText(),
                comment.getCount_likes(),
                comment.getCount_dislikes(),
                comment.getCreated_at(),
                comment.getOwner().getId(),
                comment.getOwner().getUsername(),
                comment.getOwner().getRole()
        );
    }
}
