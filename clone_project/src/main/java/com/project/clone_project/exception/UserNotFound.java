package com.project.clone_project.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super(message);
        System.out.println(message);
    }
}
