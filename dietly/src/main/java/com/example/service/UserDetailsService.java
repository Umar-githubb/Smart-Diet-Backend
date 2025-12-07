package com.example.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Entities.User;

@Service
public interface UserDetailsService {
	public UserDetails  loadUserByUsername(String userName);
}
