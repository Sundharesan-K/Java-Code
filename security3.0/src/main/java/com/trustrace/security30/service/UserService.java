package com.trustrace.security30.service;

import com.trustrace.security30.pojo.Login;
import com.trustrace.security30.pojo.User;
import com.trustrace.security30.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService {
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
//    @Autowired
    private  UserRepository userRepository;
//    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createUser(User user) {
        user.setPassword (passwordEncoder.encode (user.getPassword ()));
        if (userRepository.findByUserName (user.getUsername ())!=null){
            throw new RuntimeException ("username already exists");
        }
         userRepository.save(user);
        User user1=new User("1","sundhar","sund",null,"user");
        user1.setId (user.getId ());
        return "Active";
    }

    public List<User> getAllUser() {
        return userRepository.getAll();
    }



//    public String login(Login login) {
//        User user=userRepository.findByUserName (login.getUsername ());
//        if (user==null){
//            throw new RuntimeException ("Invalid name");
//        }else if (!login.getPassword ().equals (user.getPassword ())){
//            throw new RuntimeException ("Incorrect password");
//        }
//        return user + "";
//    }


//    public String CreateUser(User user){
//        user.setPassword (passwordEncoder.encode (user.getPassword ()));
//        userRepository.save (user);
//        return "User added SuccessFully";
//    }


}
