package com.example.Security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtUtil {

    private final String SECRET = "change_this_to_a_long_random_secret_key_1234567890"; 
    private final long EXPIRATION_MS = 1000 * 60 * 60 * 5; // 5 hours

    // ⬇️ Convert SECRET string to a secure key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ⬇️ Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ⬇️ Validate token
    public boolean validateToken(String token, String username) {
        final String user = extractUsername(token);
        return (user.equals(username) && !isTokenExpired(token));
    }

    // ⬇️ Extract username ("sub")
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ⬇️ Extract expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // ⬇️ Read any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                                  .setSigningKey(getSigningKey())
                                  .build()
                                  .parseClaimsJws(token)
                                  .getBody();
        return claimsResolver.apply(claims);
    }

    // ⬇️ Check if expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
