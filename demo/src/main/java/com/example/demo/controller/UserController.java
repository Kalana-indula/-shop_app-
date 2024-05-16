package com.example.demo.controller;

import com.example.demo.dto.UserPwdDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users=userService.getAllUsers();

        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<?> changeUserPassword(@PathVariable Long id, @RequestBody UserPwdDto userPwdDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.changePassword(id,userPwdDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }
}
