package com.goro.tabletalk.enumeration;

import lombok.Getter;

/**
 * Enumeration representing the possible states of an order.
 */
@Getter
public enum OrderStatusEnum {
    /** Order is waiting to be processed */
    PENDING("Pending"),
    
    /** Order is being prepared in the kitchen */
    IN_PROGRESS("In progress"),
    
    /** Order has been prepared and is ready for service */
    COMPLETED("Completed"),
    
    /** Order has been paid for */
    FINISHED("Paid");
    
    /** Display name of the status */
    private final String statusName;

    /**
     * Constructor for OrderStatusEnum.
     * @param statusName The display name of the status
     */
    OrderStatusEnum(String statusName) {
        this.statusName = statusName;
    }

}