package com.example.userdemo.controller;

import com.example.userdemo.model.User;
import com.example.userdemo.service.UserFetchStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users/fetch")
public class UserFetchController {

    private final UserFetchStrategy userFetchStrategy;

    public UserFetchController(
            @Qualifier("ldapUserStrategy") UserFetchStrategy userFetchStrategy) {
        this.userFetchStrategy = userFetchStrategy;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userFetchStrategy.getUserById(id);
    }

}
