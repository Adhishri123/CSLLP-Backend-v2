package com.configserverllp.csllp_learning_platform.user_service.config;

import com.configserverllp.csllp_learning_platform.user_service.service.impl.CustomUserDetail;
import com.configserverllp.csllp_learning_platform.user_service.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final CustomUserDetail userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("TOKEN = " + token);

        try {

            String email =
                    jwtService.extractUsername(token);

            if (email != null &&
                    SecurityContextHolder.getContext()
                            .getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(email);
                System.out.println("USERNAME = " + userDetails.getUsername());
                System.out.println("AUTHORITIES = " + userDetails.getAuthorities());

                if (jwtService.validateToken(
                        token,
                        userDetails.getUsername())) {
                    System.out.println("JWT VALID");
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

}
