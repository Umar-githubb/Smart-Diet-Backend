package com.example.ServiceImplementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.User;
import com.example.Repositories.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	public UserRepository userRepo; 
	
	@Override
	public User registerUser(User user){
		return userRepo.save(user);
	}
	
	@Override
	public List<User> getAllUsers(){
		return userRepo.findAll();
	};
	
	@Override
	public User getUserByUserId(Long userId) {
		return userRepo.findById(userId)
				.orElseThrow(()-> new RuntimeException("No user Found with " + userId));
	};
	
	
	
}
