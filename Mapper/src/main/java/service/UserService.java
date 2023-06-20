package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.User;

@Service
@RequiredArgsConstructor
public class UserService {
    public User createUser(User user) {
        User user1 = new User ();
        user1.setId (user.getId ());
        user1.setFirstName (user.getFirstName ());
        user1.setFirstName (user.getLastName ());
        user1.setEmail (user.getEmail ());
        return user1;
    }
}
