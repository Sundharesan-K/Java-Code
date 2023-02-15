package com.trustrace.Switchenergysystembackend.service;

import com.trustrace.Switchenergysystembackend.entity.User;
import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
     User createUser(User user) ;

    User login(UserLogin userLogin);

    List<User> getAllUser();
}
