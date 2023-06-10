package com.example.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthProvider authProvider;

    @Autowired
    public SecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);//был бы второй провайдер, можно было бы их составить здесь в цепочку
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->csrf.disable()/*TODO пока не до него*/)
                .authorizeHttpRequests(auth-> {
                    auth.requestMatchers(
                            "/hello",
                            "/auth/login",
                            "auth/registration").permitAll();//для открытых форм
                    auth.requestMatchers(
                            "auth/succesRegistPage",
                            "/home").hasAnyRole("USER","ADMIN");
//                    auth.requestMatchers("/admin").hasRole("ADMIN");
                })
                .formLogin(it-> {
                    it.loginPage("/auth/login");
                    it.loginProcessingUrl("/process_login");//сюда прийдут данные с формы "login"
                    it.failureUrl("/auth/login?error");//в случае неуспешной аутентификации url с параметром ошибки
                    it.defaultSuccessUrl("/home");//url после успешной аутентификации
                })
                .logout(it->{
                    it.logoutUrl("/logout");
                    it.logoutSuccessUrl("/auth/login"/*TODO не реализована*/);//сюда его перенаправит после логаута
                })
//                .sessionManagement(it->it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//отключение сессий нужна при jwt
                .build();
    }

    //нужен при доверии сравнения паролей спрингу без создания свих провайдеров
    //его прийдется вынести отсюда в ProjectApplication т.к. дает непонятную ошибку при создании бина провайдера
    /*@Bean
    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();//никак не шифруем пока
        return new BCryptPasswordEncoder();
    }*/
}
