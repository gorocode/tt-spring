package com.goro.tabletalk.enumeration;

import lombok.Getter;

/**
 * Enumeration of possible table locations in the restaurant.
 * Used to categorize and organize tables based on their physical location.
 */
@Getter
public enum TableLocationEnum {
    /** Tables located in the bar area of the restaurant */
    BAR_AREA("Bar area"),
    
    /** Tables located in the main indoor dining area */
    INDOOR_SEATING("Indoor seating"),
    
    /** Tables located in outdoor seating areas (e.g., patio, terrace) */
    OUTDOOR_SEATING("Outdoor seating");

    /** Display name of the table location */
    private final String tableLocation;

    /**
     * Constructs a TableLocationEnum with a display name.
     * @param tableLocation The display name of the table location
     */
    TableLocationEnum(String tableLocation) {
        this.tableLocation = tableLocation;
    }
}
