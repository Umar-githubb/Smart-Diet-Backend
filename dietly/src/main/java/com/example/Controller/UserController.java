package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.User;
import com.example.Repositories.UserRepository;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService userService;
	@Autowired
	public UserRepository userRepo;

    UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserByUserId(userId)); 
	}
	
	@PostMapping
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		User savedUser = userService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
	
	@DeleteMapping
	public void deleteUserByUsername(Long user_id) {
		userRepo.deleteById(user_id);
	}
	
	 @GetMapping("/protected")
	    public ResponseEntity<?> protectedEndpoint() {
	        return ResponseEntity.ok("This is PROTECTED endpoint");
	    }
}
