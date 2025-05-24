package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.authDTO.AuthRequest;
import com.goro.tabletalk.dto.authDTO.AuthResponse;
import com.goro.tabletalk.entity.UserEntity;
import com.goro.tabletalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service for user authentication operations.
 * Handles user login and token generation.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    /**
     * Repository for user data operations
     */
    private final UserRepository userRepository;

    /**
     * Authentication manager for validating credentials
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Service for JWT token operations
     */
    private final JwtService jwtService;

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param request the authentication request containing credentials
     * @return the authentication response with token and user details
     * @throws org.springframework.security.core.AuthenticationException if
     *                                                                   authentication
     *                                                                   fails
     */
    public AuthResponse authenticate(AuthRequest request) {
        // If authentication was successful, retrieve the validated user
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Authenticate user credentials - this will throw an exception if authentication fails
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));


        // Generate JWT token
        String jwtToken = jwtService.generateToken(user);

        // Build and return authentication response
        return AuthResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
