package com.example.userdemo.exception;

public class DBUserNotFoundException extends Exception {
    public DBUserNotFoundException(String message) {
        super(message);
    }
}

