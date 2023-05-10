package com.project.instagram2.O.service;

import com.project.instagram2.O.repository.LoginRepository;
import com.project.instagram2.O.service.validators.IdNameAndEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private IdNameAndEmailValidator idNameAndEmailValidator;
    public void login(String email, String password)throws Exception {
        idNameAndEmailValidator.isEmailExist(email);
        if (!password.equals(loginRepository.login(email))){
            throw new Exception("Invalid Password");
        }
    }
}
