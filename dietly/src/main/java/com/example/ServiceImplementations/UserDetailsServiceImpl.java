package com.example.ServiceImplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Entities.User;
import com.example.Repositories.UserRepository;
import com.example.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails  loadUserByUsername(String userName) throws UsernameNotFoundException{
		User user = userRepo.findByUserName(userName)
			 .orElseThrow(()-> new UsernameNotFoundException("Not Found."));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUserName())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
					
	}
}
