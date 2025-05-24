package com.goro.tabletalk.dto.authDTO;

import com.goro.tabletalk.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    /**
     * Username for registration, must be between 3 and 50 characters
     */
    private String username;

    /**
     * Email address for registration, must be valid format
     */
    private String email;

    /**
     * Password for registration, must meet complexity requirements
     */
    private String password;

    /**
     * User's first name
     */
    private String firstName;

    /**
     * User's last name
     */
    private String lastName;

    /**
     * User's role
     */
    private Role role;

    /**
     * User's account status
     */
    private Boolean enabled;
}
