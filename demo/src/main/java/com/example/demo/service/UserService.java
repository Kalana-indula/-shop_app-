package com.example.demo.service;

import com.example.demo.dto.UserPwdDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User>getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User changePassword (Long id, UserPwdDto userPwdDto);
}
