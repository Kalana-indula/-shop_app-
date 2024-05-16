package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){

        if(userRepository.existsByUserName(user.getUserName())){
            return ResponseEntity.badRequest().body("User name is already in user");
        }

        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        User newUser=new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(jwt);
    }
}
