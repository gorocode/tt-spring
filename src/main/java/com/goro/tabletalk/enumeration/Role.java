package com.goro.tabletalk.enumeration;

/**
 * Enumeration of user roles in the system.
 * Defines the hierarchy of permissions in the application.
 */
public enum Role {
    /**
     * Administrator with full system access and permissions
     */
    ADMIN,
    
    /**
     * Manager with access to management features and reports
     */
    MANAGER,
    
    /**
     * Worker with access to operational features
     */
    WORKER,
    
    /**
     * Registered customer with access to customer-specific features
     */
    CUSTOMER,
    
    /**
     * Guest with limited access to public features only
     */
    GUEST
}
