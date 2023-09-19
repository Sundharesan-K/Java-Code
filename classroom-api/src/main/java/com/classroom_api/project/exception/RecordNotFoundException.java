package com.classroom_api.project.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(){
        super();
    }
    public RecordNotFoundException(String message){
        super(message);
    }
}
