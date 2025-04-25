package com.example.userdemo.service.impl;

import com.example.userdemo.model.User;
import com.example.userdemo.repository.UserRepository;
import com.example.userdemo.service.UserFetchStrategy;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class DBUserStrategy implements UserFetchStrategy {

    private final UserRepository userRepository;

    public DBUserStrategy(UserRepository userRepository)
    {
        this.userRepository= userRepository;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.getUsersById(id);
    }
}
