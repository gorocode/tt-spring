package com.goro.tabletalk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsible for providing health check endpoints to monitor the application status.
 * Health checks are essential for production environments to verify system availability.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {
    
    /**
     * Basic health check endpoint that returns the current status of the application.
     * This endpoint can be used by monitoring systems to verify the application is running.
     * 
     * @return ResponseEntity containing a map with status information and timestamp
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", "TableTalk Backend");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
