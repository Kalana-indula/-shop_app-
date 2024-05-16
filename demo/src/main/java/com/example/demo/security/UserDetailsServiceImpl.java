package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user=userRepository.findByUserName(userName).orElse(null);

        if(user==null){
            throw new UsernameNotFoundException("User not found with given user name");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}
