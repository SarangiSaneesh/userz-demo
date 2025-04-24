package com.example.userdemo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LDAPUser {
    private String catercId;
    private String email;
    private String givenName;

    public LDAPUser(String catercId, String email, String givenName) {
        this.catercId = catercId;
        this.email = email;
        this.givenName = givenName;
    }
}
