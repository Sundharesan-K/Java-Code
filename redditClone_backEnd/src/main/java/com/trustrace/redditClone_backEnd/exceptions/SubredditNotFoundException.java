package com.trustrace.redditClone_backEnd.exceptions;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String message){
        super(message);
    }
}
