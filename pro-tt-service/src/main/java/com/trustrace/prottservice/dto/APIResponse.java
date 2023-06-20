package com.trustrace.prottservice.dto;

import lombok.Data;

@Data
public class APIResponse {
    private  String status;
    private  String errorCode;
    private  String message;
    private Object errors;
    private Object data;

    public APIResponse() {
    }

    public APIResponse(String message, Object data){
        this.message=message;
        this.data=data;
    }

    public APIResponse(String message, Object data, String status){
        this.data=data;
        this.message=message;
        this.status=status;
    }

    public APIResponse(String status, String message)
    {
        this.status=status;
        this.message=message;
    }

}
