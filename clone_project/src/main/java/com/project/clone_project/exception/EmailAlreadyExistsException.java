package com.project.clone_project.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
        System.out.println(message);
    }
}
