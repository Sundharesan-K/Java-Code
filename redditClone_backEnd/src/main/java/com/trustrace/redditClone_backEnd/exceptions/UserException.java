package com.trustrace.redditClone_backEnd.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserException extends RuntimeException{
    public UserException(String message){
        super(message);
        log.info(message);
    }
}
