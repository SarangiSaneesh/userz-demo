package com.example.userdemo.service;

import com.example.userdemo.model.User;

import java.util.Optional;

public interface UserFetchStrategy {
    Optional<User> getUserById(String id);
}
