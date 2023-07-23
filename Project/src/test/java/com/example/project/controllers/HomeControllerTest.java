package com.example.project.controllers;

import com.example.project.security.PersonDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.config.http.MatcherType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WithMockUser
@ExtendWith(MockitoExtension.class)
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @InjectMocks
    HomeController controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    /**
     * интеграционный тест
     */
    @Test
    void homePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home/home"));
    }

    /**
     * унит тест
     */
    @Test
    void homePage2() throws Exception {
        Model model=Mockito.mock(Model.class);

        Field field=controller.getClass().getDeclaredField("httpServletRequest");
        field.setAccessible(true);
        HttpServletRequest requestMock=Mockito.mock(HttpServletRequest.class);
        field.set(controller,requestMock);
        Principal principalMock=Mockito.mock(Principal.class);
        when(requestMock.getUserPrincipal()).thenReturn(principalMock);
        when(principalMock.getName()).thenReturn("any user");

        assertEquals("home/home",controller.homePage(model));

        verify(requestMock,times(1)).getUserPrincipal();
        verify(model,times(1)).addAttribute("username", "any user");
        verify(principalMock,times(1)).getName();
    }

    /**
     * интограционный тест
     */
    @Test
    void getCommentPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home/comment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home/comment"));
    }
    /**
     * унит тест
     */
    @Test
    void getCommentPage2(){
        assertEquals("home/comment",controller.getCommentPage());
    }
}