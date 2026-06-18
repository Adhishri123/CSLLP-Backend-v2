package com.configserverllp.configpulse.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret:mySecretKeyForJwtGenerationAndValidation123456}")
    private String secret;

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        Claims claims = extractClaims(token);
        Object role = claims.get("role");
        return role != null ? role.toString() : "USER";
    }

    public Long extractUserId(String token) {
        Claims claims = extractClaims(token);
        Object userId = claims.get("userId");
        return userId != null ? Long.valueOf(userId.toString()) : 0L;
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, String email) {
        try {
            String extractedEmail = extractUsername(token);
            return extractedEmail.equals(email) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}