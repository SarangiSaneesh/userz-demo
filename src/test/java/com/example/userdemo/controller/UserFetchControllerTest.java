package com.example.userdemo.controller;

import com.example.userdemo.model.LDAPUser;
import com.example.userdemo.model.User;
import com.example.userdemo.service.UserFetchStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserFetchController.class)
public class UserFetchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserFetchStrategy userFetchStrategy;

    @Test
    public void testGetUserById() throws Exception {
        User mockUser = new User("1","sarangisaneesh44@gmail.com","sarangi");
        when(userFetchStrategy.getUserById("1")).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/fetch/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.email").value(("sarangisaneesh44@gmail.com")))
                .andExpect(jsonPath("$.firstname").value("sarangi"));
    }

    @Test
    public void shouldReturn404IfUserNotFound() throws Exception{

        when(userFetchStrategy.getUserById("99")).thenReturn(Optional.empty());
        mockMvc.perform(get("/users/fetch/99")).andExpect(status().isNotFound());

   }

}
