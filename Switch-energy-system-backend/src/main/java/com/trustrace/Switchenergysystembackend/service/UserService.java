package com.trustrace.Switchenergysystembackend.service;

import com.trustrace.Switchenergysystembackend.entity.User;
import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    String addUser(UserLogin userLogin);
     User createUser(User user) ;

    Optional<UserLogin> login(UserLogin userLogin);

    List<User> getAllUser();
}
