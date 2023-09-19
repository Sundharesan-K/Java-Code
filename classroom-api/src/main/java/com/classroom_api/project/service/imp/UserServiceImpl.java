package com.classroom_api.project.service.imp;

import com.classroom_api.project.entity.User;
import com.classroom_api.project.exception.RecordNotFoundException;
import com.classroom_api.project.mapper.UserMapper;
import com.classroom_api.project.model.user.UserResponseDTO;
import com.classroom_api.project.repository.UserRepository;
import com.classroom_api.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponseDTO getUser(String id) {
      var user =  userRepository.findById(id).orElseThrow(RecordNotFoundException::new);
      return userMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getUsersByName(String userName) {
        List<User> user = userRepository.findByUserName(userName).orElseThrow(RecordNotFoundException::new);
        return user.stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RecordNotFoundException::new);
        return userMapper.toDTO(user);
    }
}

