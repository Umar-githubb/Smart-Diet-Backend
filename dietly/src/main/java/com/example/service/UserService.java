package com.example.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.Entities.User;

@Service
public interface UserService {
	User registerUser(User user);
	List<User> getAllUsers();
	User getUserByUserId(Long userId);
	
}
