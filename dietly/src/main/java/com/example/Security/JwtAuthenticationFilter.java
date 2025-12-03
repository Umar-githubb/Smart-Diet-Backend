package com.example.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ServiceImplementations.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsService;
	
	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response , FilterChain filterChain) 
					throws ServletException, IOException {
		
		
		//Read Jwt Token from Request header Authorization : xxxx
		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		String username = null;
		//check if Authorization starts with Bearer
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			//remove Bearer part from the token
			token = authHeader.substring(7);
			
			username = jwtUtil.extractUsername(token);
		}
		// Validate token
		// guard takes the card (token) from the student and checks if the user is in the database
		//
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateToken(token, userDetails.getUsername())) {
				// create authentication object for example guard gives a unique pass to enter the clg
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				// Set authentication in security context
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		//“Okay, next guard, your turn!”
		filterChain.doFilter(request, response);
	}
}
