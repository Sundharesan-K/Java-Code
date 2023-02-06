package com.trustrace.DetailsSpringBoot.service;

import com.trustrace.DetailsSpringBoot.pojo.User;
import com.trustrace.DetailsSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User CreateUser(User user) {
        return userRepository.CreateUser (user);
    }

    public List<User> getAllUser() {
        return userRepository.getAllUser ();
    }

    public User getByUserId(String id) {
        return userRepository.getByUserId (id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public String deleteUserId(String id) {
        return userRepository.deleteUserId(id);
    }
}
