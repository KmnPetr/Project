package com.example.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
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
}

@Configuration
class SecurityConfig2{
//    private final AuthProvider authProvider;
//    @Autowired
//    public SecurityConfig2(AuthProvider authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth){
//        auth.authenticationProvider(authProvider);//был бы второй провайдер, можно было бы их составить здесь в цепочку
//    }
    @Bean
    //понадобился для ручной аутентификации
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)throws Exception{
        return authConfig.getAuthenticationManager();
    }
    //вынес сюда, чтобы не создавать циклицескую зависимость в SecurityConfig
    @Bean
    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();//никак не шифруем
        return new BCryptPasswordEncoder();
    }
}