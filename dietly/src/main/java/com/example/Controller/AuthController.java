package com.example.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.LoginRequest;
import com.example.Dto.LoginResponse;
import com.example.Security.JwtUtil;
import com.example.demo.DemoApplication;
import com.example.service.UserDetailsService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final DemoApplication demoApplication;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserDetailsService userDetailsService, DemoApplication demoApplication) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.demoApplication = demoApplication;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // 1️⃣ Authenticate user (username + password)
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2️⃣ If authentication successful, load full user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // 3️⃣ Generate JWT
        String token = jwtUtil.generateToken(userDetails.getUsername());

        // 4️⃣ Return token
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
