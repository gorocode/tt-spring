package com.goro.tabletalk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementation of EmailService for sending emails.
 * Uses Spring's JavaMailSender for email operations.
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    @Value("${FRONTEND_URL}")
    private String frontendUrl;

    /**
     * Sends a simple text email.
     *
     * @param to recipient email address
     * @param subject email subject
     * @param text email body text
     * @return true if email was sent successfully, false otherwise
     */
    @Override
    public boolean sendSimpleEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            // Log exception in a real implementation
            return false;
        }
    }

    /**
     * Sends a password reset email with a reset link.
     *
     * @param to recipient email address
     * @param token password reset token
     * @return true if email was sent successfully, false otherwise
     */
    @Override
    public boolean sendPasswordResetEmail(String to, String token) {
        String resetUrl = frontendUrl + "/reset-password?token=" + token;
        String subject = "TableTalk - Password Reset";
        String text = "Hi,\n\n" +
                "You requested a password reset for your TableTalk account.\n\n" +
                "Please click on the link below to reset your password:\n\n" +
                resetUrl + "\n\n" +
                "This link will expire in 1 hour.\n\n" +
                "If you did not request a password reset, please ignore this email.\n\n" +
                "Regards,\n" +
                "TableTalk Team";
        
        return sendSimpleEmail(to, subject, text);
    }
}
