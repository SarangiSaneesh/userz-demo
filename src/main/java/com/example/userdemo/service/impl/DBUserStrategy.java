package com.example.userdemo.service.impl;

import com.example.userdemo.exception.DBUserNotFoundException;
import com.example.userdemo.model.User;
import com.example.userdemo.repository.UserRepository;
import com.example.userdemo.service.UserFetchStrategy;


import java.util.Optional;


public class DBUserStrategy implements UserFetchStrategy {

    private final UserRepository userRepository;

    public DBUserStrategy(UserRepository userRepository)
    {
        this.userRepository= userRepository;
    }

    @Override
    public Optional<User> getUserById(String id) throws DBUserNotFoundException{

        return Optional.ofNullable(userRepository.getUsersById(id).orElseThrow(() -> new DBUserNotFoundException("DB user not found with id: " + id)));
    }
}
