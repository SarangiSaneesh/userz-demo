package com.example.userdemo.controller;

import com.example.userdemo.exception.DBUserNotFoundException;
import com.example.userdemo.exception.LdapUserNotFoundException;
import com.example.userdemo.exception.UserNotFoundException;
import com.example.userdemo.model.User;
import com.example.userdemo.service.UserFetchStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users/fetch")
public class UserFetchController {

    private final UserFetchStrategy strategy;

    public UserFetchController(
            @Value("${app.user.strategy}") String strategyName,
            Map<String, UserFetchStrategy> strategyMap) {
        this.strategy = strategyMap.get(strategyName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws Exception {

        try {
            Optional<User> user = strategy.getUserById(id);
            return user.map(ResponseEntity::ok)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        } catch (LdapUserNotFoundException | DBUserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

}
