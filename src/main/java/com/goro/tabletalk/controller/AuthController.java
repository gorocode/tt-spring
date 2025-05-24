package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.authDTO.AuthRequest;
import com.goro.tabletalk.dto.authDTO.AuthResponse;
import com.goro.tabletalk.dto.authDTO.ForgotPasswordRequest;
import com.goro.tabletalk.dto.authDTO.ResetPasswordRequest;
import com.goro.tabletalk.dto.authDTO.UserRegistrationRequest;
import com.goro.tabletalk.service.AuthenticationService;
import com.goro.tabletalk.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication-related endpoints.
 * Handles user registration, login, and password management.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    /**
     * Registers a new user.
     * 
     * @param request the registration details
     * @return the authentication response with JWT token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody UserRegistrationRequest request
                                                ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    /**
     * Authenticates a user and provides a JWT token.
     * 
     * @param request the authentication credentials
     * @return the authentication response with JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * Initiates the password reset process by sending a reset token to the user's email.
     * 
     * @param request containing the user's email
     * @return success or error message
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        boolean result = userService.generatePasswordResetToken(request.getEmail());
        
        if (result) {
            return ResponseEntity.ok("Password reset instructions sent to your email");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Email not found");
        }
    }

    /**
     * Validates a password reset token.
     * 
     * @param token the reset token to validate
     * @return success or error message
     */
    @GetMapping("/validate-reset-token")
    public ResponseEntity<String> validateResetToken(@RequestParam String token) {
        boolean isValid = userService.validatePasswordResetToken(token);
        
        if (isValid) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid or expired token");
        }
    }

    /**
     * Resets a user's password using a valid reset token.
     * 
     * @param request containing the token and new password
     * @return success or error message
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        boolean result = userService.resetPassword(request.getToken(), request.getNewPassword());
        
        if (result) {
            return ResponseEntity.ok("Password has been reset successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to reset password. Token may be invalid or expired");
        }
    }
}
