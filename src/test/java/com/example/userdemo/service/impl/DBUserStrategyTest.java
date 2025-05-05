package com.example.userdemo.service.impl;

import com.example.userdemo.exception.DBUserNotFoundException;
import com.example.userdemo.model.User;
import com.example.userdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DBUserStrategyTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DBUserStrategy dbUserStrategy;

    public DBUserStrategyTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_sucess() throws DBUserNotFoundException {
        User mockUser =new User("1","laala@gmail.com","laala");
        when(userRepository.getUsersById("1")).thenReturn(Optional.of(mockUser));
        Optional<User> result = dbUserStrategy.getUserById("1");

        assertTrue(result.isPresent());
        assertEquals("1",result.get().getId());
        assertEquals("laala@gmail.com",result.get().getEmail());
        assertEquals("laala",result.get().getFirstname());
    }

    @Test
    public void testGetUserById_notFound() throws DBUserNotFoundException{
        when(userRepository.getUsersById("404")).thenReturn(Optional.empty());
        Exception exception = assertThrows(DBUserNotFoundException.class,
                () -> {dbUserStrategy.getUserById("404");});
        assertEquals("DB user not found with id: 404",exception.getMessage());
    }
}
