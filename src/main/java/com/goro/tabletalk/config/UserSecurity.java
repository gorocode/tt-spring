package com.goro.tabletalk.config;

import com.goro.tabletalk.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Security utility component for user-specific access checks.
 * Used in method security annotations to determine if a user can access specific resources.
 */
@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {

    /**
     * Checks if the current authenticated user is the same as the requested user ID.
     * Used for allowing users to access their own resources.
     *
     * @param userId the ID of the user whose resources are being accessed
     * @return true if the current user is accessing their own resources
     */
    public boolean isCurrentUser(UUID userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserEntity) {
            return ((UserEntity) principal).getId().equals(userId);
        }
        
        return false;
    }
}
