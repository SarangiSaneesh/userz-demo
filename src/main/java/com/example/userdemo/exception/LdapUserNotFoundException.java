package com.example.userdemo.exception;

public class LdapUserNotFoundException extends RuntimeException {
    public LdapUserNotFoundException(String message) {
        super(message);
    }
}
