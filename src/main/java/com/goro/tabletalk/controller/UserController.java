package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.authDTO.UserRegistrationRequest;
import com.goro.tabletalk.dto.authDTO.UserUpdateRequest;
import com.goro.tabletalk.entity.UserEntity;
import com.goro.tabletalk.enumeration.Role;
import com.goro.tabletalk.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for user management operations.
 * Provides endpoints for retrieving and managing users.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user.
     * Only accessible to administrators.
     *
     * @return ResponseEntity containing the created user
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> postUser(@Valid @RequestBody UserRegistrationRequest user, @RequestParam Role role) {
        return ResponseEntity.ok(userService.saveUser(user, role));
    }

    /**
     * Updates a user.
     * Only accessible to administrators.
     *
     * @return ResponseEntity containing the created user
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> putUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    /**
     * Retrieves all users in the system.
     * Only accessible to administrators.
     * 
     * @return list of all users
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves a user by their ID.
     * Accessible to administrators and the user themselves.
     * 
     * @param id the user's ID
     * @return the user if found
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isCurrentUser(#id)")
    public ResponseEntity<UserEntity> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the current authenticated user.
     * 
     * @param principal the authenticated user
     * @return the current user
     */
    @GetMapping("/me")
    public ResponseEntity<UserEntity> getCurrentUser(@RequestAttribute("user") UserEntity user) {
        return ResponseEntity.ok(user);
    }

    /**
     * Deletes a user.
     * Only accessible to administrators.
     * 
     * @param id the user's ID
     * @return the deleted user
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SuccessResponse<String>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new SuccessResponse<>("Success", "User deleted successfully"));
    }
}
