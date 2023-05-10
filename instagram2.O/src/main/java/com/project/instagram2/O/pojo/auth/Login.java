package com.project.instagram2.O.pojo.auth;

import lombok.Data;

import java.util.Date;

@Data
public class Login {
    private final String email;
    private final String password;
    private final Date loggedInDate;
    public Login(String email,String password){
        this.email=email;
        this.password = password;
        this.loggedInDate=new Date();
    }
}
