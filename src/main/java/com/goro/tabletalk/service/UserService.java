package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.authDTO.AuthResponse;
import com.goro.tabletalk.dto.authDTO.UserRegistrationRequest;
import com.goro.tabletalk.dto.authDTO.UserUpdateRequest;
import com.goro.tabletalk.entity.UserEntity;
import com.goro.tabletalk.enumeration.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for user management operations.
 * Provides methods for user registration, retrieval, and management.
 */
public interface UserService {

    /**
     * Saves a new user with specified role.
     *
     * @param request the user registration request
     * @param role the role to assign
     * @return The created user
     */
    UserEntity saveUser(UserRegistrationRequest request, Role role);

    /**
     * Updates user.
     *
     * @param id the user id to update
     * @param request the user registration request
     * @param role the role to assign
     * @return The created user
     */
    UserEntity updateUser(UUID id, UserUpdateRequest request);

    /**
     * Registers a new user with CUSTOMER role.
     *
     * @param request the user registration request
     * @return the authentication response containing token and user details
     */
    AuthResponse registerUser(UserRegistrationRequest request);

    /**
     * Finds a user by their ID.
     *
     * @param id the user's unique identifier
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<UserEntity> findById(UUID id);

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<UserEntity> findByUsername(String username);
    
    /**
     * Finds a user by their email.
     *
     * @param email the email to search for
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Checks if a username is already taken.
     *
     * @param username the username to check
     * @return true if the username is taken, false otherwise
     */
    boolean isUsernameTaken(String username);

    /**
     * Checks if an email is already registered.
     *
     * @param email the email to check
     * @return true if the email is registered, false otherwise
     */
    boolean isEmailRegistered(String email);

    /**
     * Gets all users in the system.
     *
     * @return a list of all users
     */
    List<UserEntity> getAllUsers();

    /**
     * Generates a password reset token for a user.
     *
     * @param email the email of the user requesting password reset
     * @return true if token was generated successfully, false otherwise
     */
    boolean generatePasswordResetToken(String email);

    /**
     * Validates a password reset token.
     *
     * @param token the token to validate
     * @return true if the token is valid, false otherwise
     */
    boolean validatePasswordResetToken(String token);

    /**
     * Resets a user's password using a valid reset token.
     *
     * @param token the reset token
     * @param newPassword the new password
     * @return true if password reset was successful, false otherwise
     */
    boolean resetPassword(String token, String newPassword);

    /**
     * Deletes a user.
     *
     * @param id the user's ID
     */
    void deleteUser(UUID id);
}
