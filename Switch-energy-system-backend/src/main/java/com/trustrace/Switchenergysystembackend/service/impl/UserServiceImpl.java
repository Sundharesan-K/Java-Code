package com.trustrace.Switchenergysystembackend.service.impl;

import com.trustrace.Switchenergysystembackend.entity.User;
import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import com.trustrace.Switchenergysystembackend.repository.UserRepository;
import com.trustrace.Switchenergysystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public String addUser(UserLogin userLogin) {
//        userLogin.setPassword (passwordEncoder.encode (userLogin.getPassword ()));
//        return "SuccessFul added";
//    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByName (user.getName ())!=null){
            throw new RuntimeException ("Email Id already exists");
        }
        user.setStatus ("Active");
        return userRepository.save (user);
    }

    @Override
    public Optional<UserLogin> login(UserLogin userLogin) {
        Optional<UserLogin> user=userRepository.findByName (userLogin.getName ());
        if (user==null){
            throw new RuntimeException ("Invalid Id");
        }else if (!userLogin.getPassword ().equals (user. get ())){
            throw new RuntimeException ("Wrong Password");
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll ();
    }
}
