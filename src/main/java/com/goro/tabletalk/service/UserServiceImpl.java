package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.authDTO.AuthResponse;
import com.goro.tabletalk.dto.authDTO.UserRegistrationRequest;
import com.goro.tabletalk.dto.authDTO.UserUpdateRequest;
import com.goro.tabletalk.entity.UserEntity;
import com.goro.tabletalk.enumeration.Role;
import com.goro.tabletalk.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the UserService interface.
 * Handles all user-related operations including registration, retrieval, and password management.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * Repository for user data operations
     */
    private final UserRepository userRepository;
    
    /**
     * Service for JWT token operations
     */
    private final JwtService jwtService;
    
    /**
     * Encoder for password hashing
     */
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserRegistrationRequest request, Role role) {
        if (isUsernameTaken(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        
        if (isEmailRegistered(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }
        
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(role)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UUID id, UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        if (isUsernameTaken(request.getUsername()) && !user.getUsername().equals(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        
        if (isEmailRegistered(request.getEmail()) && !user.getEmail().equals(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getEnabled() != null) {
            user.setEnabled(request.getEnabled());
        }

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }

        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public AuthResponse registerUser(UserRegistrationRequest request) {
        if (isUsernameTaken(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        
        if (isEmailRegistered(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }
        
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.CUSTOMER) // Default role for new registrations
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        
        UserEntity savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(savedUser);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean generatePasswordResetToken(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        
        UserEntity user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpiry(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
        userRepository.save(user);
        
        // In a real implementation, you would send an email with the reset link
        // containing the token to the user's email address
        
        return true;
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        Optional<UserEntity> userOptional = userRepository.findByResetPasswordToken(token);
        if (userOptional.isEmpty()) {
            return false;
        }
        
        UserEntity user = userOptional.get();
        if (user.getResetPasswordTokenExpiry().isBefore(LocalDateTime.now())) {
            // Token has expired
            return false;
        }
        
        return true;
    }

    @Override
    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        if (!validatePasswordResetToken(token)) {
            return false;
        }
        
        Optional<UserEntity> userOptional = userRepository.findByResetPasswordToken(token);
        if (userOptional.isEmpty()) {
            return false;
        }
        
        UserEntity user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiry(null);
        userRepository.save(user);
        
        return true;
    }

    @Override
    public void deleteUser(UUID id) {
        if (id.equals(UUID.fromString("d6a3dcb4-7727-4eba-999e-0431a6cda204"))) {
            throw new IllegalArgumentException("Cannot delete admin user");
        }
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        
        UserEntity user = userOptional.get();
        userRepository.delete(user);
    }
}
