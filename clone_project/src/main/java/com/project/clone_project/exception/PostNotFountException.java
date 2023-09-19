package com.project.clone_project.exception;

public class PostNotFountException extends RuntimeException {
    public PostNotFountException(String message) {
        super(message);
        System.out.println(message);
    }
}
