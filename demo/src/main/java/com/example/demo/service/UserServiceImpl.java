package com.example.demo.service;

import com.example.demo.dto.UserPwdDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User changePassword(Long id, UserPwdDto userPwdDto) {
        User user= userRepository.findById(id).orElse(null);

        if(user !=null){
            user.setPassword(userPwdDto.getPassword());

            return userRepository.save(user);
        }else{
            return null;
        }
    }


}
