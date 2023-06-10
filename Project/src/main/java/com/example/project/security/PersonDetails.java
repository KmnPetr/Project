package com.example.project.security;

import com.example.project.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {return true;/*не просрочен*/}
    @Override
    public boolean isAccountNonLocked() {return true;/*не заблочен*/}
    @Override
    public boolean isCredentialsNonExpired() {return true;/*пароль не просрочен*/}
    @Override
    public boolean isEnabled() {return true;/*аккаунт включен и работает*/}

    public Person getPerson(){return this.person;}//нужен чтобы получать данные аутентифицированного пользователя
}
