package com.classroom_api.project.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message){
        super(message);
    }
}
