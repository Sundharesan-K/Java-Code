package com.trustrace.redditClone_backEnd.exceptions;

public class UserException extends RuntimeException{
    public UserException(String message){
        super(message);
        System.out.println (message);
    }
}
