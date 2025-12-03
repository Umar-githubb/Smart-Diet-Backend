package com.example.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
   Optional<User> findByEmail(String email);
   
   Optional<User> findByUserName(String userName);
}
