package com.goro.tabletalk.dto.authDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for authentication requests.
 * Contains credentials required for user login.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    
    /**
     * Username for authentication
     */
    @NotBlank(message = "Username cannot be blank")
    private String username;
    
    /**
     * Password for authentication
     */
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
