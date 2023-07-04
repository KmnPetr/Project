package com.example.project.services;

import com.example.project.models.LikeAction;
import com.example.project.models.Person;
import com.example.project.repositories.LikeRepository;
import com.example.project.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LikesService {
    private final LikeRepository likeRepository;
    @Autowired
    public LikesService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Transactional
    public void saveLikeAction(int comment_id,String action){
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();
        System.out.println("достали person "+ person);

        if (action.equals("like")||action.equals("dislike")){
            likeRepository.save(new LikeAction(person.getId(),comment_id,action));
            //hibernete сам разберется, существует ли уже действие like/dislike
            // и при необходимости обновит с новым значением action
            // или создаст новую запись
            //удобненько.. да?
        }else{
            throw new IllegalArgumentException("parametr \"action\" should be equals\"like\" or \"dislike\"");
        }
    }
}
