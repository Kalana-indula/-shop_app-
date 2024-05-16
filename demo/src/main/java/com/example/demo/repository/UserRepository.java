package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //checking whether a user exists
    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
