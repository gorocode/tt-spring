package com.goro.tabletalk.dto.authDTO;

import com.goro.tabletalk.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object for authentication responses.
 * Contains the JWT token and basic user information returned after successful authentication.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    /**
     * JWT access token for authentication
     */
    private String token;
    
    /**
     * User's unique identifier
     */
    private UUID userId;
    
    /**
     * User's username
     */
    private String username;
    
    /**
     * User's email
     */
    private String email;
    
    /**
     * User's role in the system
     */
    private Role role;
}
