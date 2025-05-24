package com.goro.tabletalk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for WebSocket communication.
 * Sets up STOMP protocol endpoints and message broker for real-time
 * communication between the server and clients.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /** URL of the frontend application for CORS configuration */
    @Value("${FRONTEND_URL}")
    private String frontendUrl;

    /**
     * Configures the message broker for WebSocket communication.
     * Sets up a simple in-memory broker with topic prefix '/topic'
     * and application destination prefix '/app'.
     * 
     * @param config The message broker registry to configure
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * Registers STOMP endpoints for WebSocket communication.
     * Sets up the '/ws' endpoint with CORS configuration for the frontend.
     * 
     * @param registry The STOMP endpoint registry to configure
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");
    }
}
