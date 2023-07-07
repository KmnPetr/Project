package com.example.project.util;

import com.example.project.models.Person;
import com.example.project.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * класс проверяет на уникальность регистрации имени нового человека
 */
@Component
public class PersonValidator implements Validator {
    private final PeopleService personService;
    @Autowired
    public PersonValidator(PeopleService personService) {
        this.personService = personService;
    }

    /**
     * нужен для определения того класса который хотим валидировать
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    /**
     * метод делает запрос в БД, существует ли Person c таким email
     * или не соответствует ли username зарезервированному логину
     * если да, то кладет ошибку
     */
    @Override
    public void validate(Object target, Errors errors) {
        Person person=(Person)target;

        if(personService.findByUsername(person.getUsername())!=null || person.getUsername().equals(SomeEnams.RESERVED_LOGIN.getValue()))
            errors.rejectValue("username"/*на каком поле*/,
                    "",
                    "This username is already taken."/*сообщение*/);
    }
}
