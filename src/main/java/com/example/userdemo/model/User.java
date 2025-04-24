package com.example.userdemo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;
    private String email;
    private String firstname;

    public User(String id, String email, String firstname) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
    }


}
