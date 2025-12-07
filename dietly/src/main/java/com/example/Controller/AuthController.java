package com.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.Dto.LoginRequest;
import com.example.Dto.LoginResponse;
import com.example.Entities.User;
import com.example.Security.JwtUtil;
import com.example.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserDetailsService userDetailsService,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        // 1️⃣ Authenticate username + password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2️⃣ Load UserDetails
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        // 3️⃣ Fetch full User entity (contains ROLE enum)
        User user = userService.findByUsername(request.getUsername());

        // 4️⃣ Generate JWT that includes roles inside
        String token = jwtUtil.generateToken(user);

        // 5️⃣ Return token
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
