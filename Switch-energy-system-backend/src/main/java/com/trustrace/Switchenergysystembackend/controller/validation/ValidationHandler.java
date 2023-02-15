package com.trustrace.Switchenergysystembackend.controller.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler  {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(@org.jetbrains.annotations.NotNull MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        Map<String,String> errors=new HashMap<> ();
        exception.getBindingResult ().getAllErrors ().forEach ((error)->{
            String fieldName=((FieldError) error).getField ();
            String message=error.getDefaultMessage ();

            errors.put (fieldName,message);
        });
        return new ResponseEntity<Object> (errors,HttpStatus.BAD_REQUEST);
    }
}
