package com.example.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * здесь сравниваются аутентификационные данные пришедшие с формы с теми что лежат в БД
 */
@Component
public class AuthProvider implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthProvider(PersonDetailsService personDetailsService, PasswordEncoder passwordEncoder) {
        this.personDetailsService = personDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    //вернет principal, если аутентификация пройдет успешно
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username= authentication.getName();

        UserDetails personDetails=personDetailsService.loadUserByUsername(username);
        String password=authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,personDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password!");//TODO
        }

        return new UsernamePasswordAuthenticationToken(
                personDetails,
                null,
                personDetails.getAuthorities());
    }

    //нужен если в приложении несколько провайдеров аутентификации
    //определяет, может ли данный провайдер обрабатывать Authentication, или его передать следующему по списку провайдеру
    //в данном приложении довольно бесполезен
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
