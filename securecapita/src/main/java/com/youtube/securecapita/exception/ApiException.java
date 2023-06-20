package com.youtube.securecapita.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
