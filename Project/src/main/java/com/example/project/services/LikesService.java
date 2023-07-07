package com.example.project.services;

import com.example.project.models.LikeAction;
import com.example.project.models.LikeActionId;
import com.example.project.models.Person;
import com.example.project.repositories.LikeRepository;
import com.example.project.security.PersonDetails;
import com.example.project.util.SomeEnams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LikesService {
    private final LikeRepository likeRepository;
    @Autowired
    public LikesService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Transactional
    public void saveLikeAction(int comment_id,String type){
        PersonDetails personDetails=(PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person=personDetails.getPerson();

        //проверяем, существует ли уже такой же лайк
        LikeAction likeAction=likeRepository.findById(new LikeActionId(person.getId(), comment_id)).orElse(null);

        if (type.equals(SomeEnams.LIKE.getValue())||type.equals(SomeEnams.DISLIKE.getValue())){
            if (likeAction!=null&&likeAction.getType().equals(type)){
                //удалим лайк если пользователь второй раз нажал на ту же кнопку
                likeRepository.deleteById(new LikeActionId(person.getId(), comment_id));
            }else {
                likeRepository.save(new LikeAction(person.getId(),comment_id,type));
                //hibernete сам разберется, существует ли уже действие like/dislike
                // и при необходимости обновит с новым значением action
                // или создаст новую запись
                //удобненько.. да?
            }
        }else{
            throw new IllegalArgumentException("parametr \"type\" should be equals\""+SomeEnams.LIKE.getValue()+"\" or \""+SomeEnams.DISLIKE.getValue()+"\"");
        }
    }

    public List<LikeAction> getAllLikes() {
        return likeRepository.findAll();
    }
}
