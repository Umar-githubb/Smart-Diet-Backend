package com.example.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
    @NotBlank(message = "User Name cannot be empty.")
    private String userName;
	
    @Email(message = "email must be in xxx.com format.")
    @Column(unique = true)
    private String email;
    
    private String password;
	
    @Enumerated(EnumType.STRING)
    private Role role;   //ADMIN, USER, DIETICIAN

	public User(Long userId, @NotBlank(message = "User Name cannot be empty.") String userName,
			@Email(message = "email must be in xxx.com format.") String email, Role role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
