package com.project.clone_project.exception;

public class SubredditNotFoundException extends RuntimeException{

    public SubredditNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
