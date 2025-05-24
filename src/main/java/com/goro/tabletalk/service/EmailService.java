package com.goro.tabletalk.service;

/**
 * Service interface for sending emails.
 * Used for sending notifications, password reset emails, etc.
 */
public interface EmailService {
    
    /**
     * Sends a simple text email.
     *
     * @param to recipient email address
     * @param subject email subject
     * @param text email body text
     * @return true if email was sent successfully, false otherwise
     */
    boolean sendSimpleEmail(String to, String subject, String text);
    
    /**
     * Sends a password reset email with a reset link.
     *
     * @param to recipient email address
     * @param token password reset token
     * @return true if email was sent successfully, false otherwise
     */
    boolean sendPasswordResetEmail(String to, String token);
}
