package com.example.userdemo.repository;

import com.example.userdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Map<String, User> userStore = new HashMap<>();

    static {
        userStore.put("1", new User("1", "user1@example.com", "John"));
        userStore.put("2", new User("2", "user2@example.com", "Jane"));
    }

    public Optional<User> getUsersById(String id) {
        return Optional.ofNullable(userStore.get(id));
    }

}
