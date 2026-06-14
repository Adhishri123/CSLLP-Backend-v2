package com.configserverllp.csllp_learning_platform.user_service.service.impl;

import com.configserverllp.csllp_learning_platform.user_service.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private static final String SECRET =
            "mySecretKeyForJwtGenerationAndValidation123456";// expire = 24*60*60*1000

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(
                        Keys.hmacShaKeyFor(SECRET.getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(
//                        Keys.hmacShaKeyFor(SECRET.getBytes())
//                )
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(SECRET.getBytes())
                )
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, String email) {
        return extractUsername(token).equals(email);
    }

//    private static final String SECRET_KEY = "MyVeryStrongSecretKeyForJwtAuthentication2026LearningPlatform";
//    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;
//    private static final long REFRESH_TOKEN_EXPIRATION = 48 * 60 * 60 * 1000;
//
//    //GENERATE TOKEN
//    public String generateToken(String username, boolean isAccessToken) {
//        long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // GENERATE NAME FROM TOKEN
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    //VALIDATE
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
//            return true;
//        } catch(JwtException ex) {
//            return false;
//        }
//    }

}
