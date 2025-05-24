package com.goro.tabletalk.enumeration;

import lombok.Getter;

/**
 * Enumeration of common food allergens.
 * Used to identify and track potential allergens in menu items.
 * Based on the EU Food Information for Consumers Regulation (EU FIC).
 */
@Getter
public enum AllergenEnum {
    /** Cereals containing gluten (wheat, rye, barley, oats) */
    GLUTEN("Gluten"),
    
    /** Crustaceans and products thereof (e.g., prawns, crabs, lobster) */
    CRUSTACEAN("Crustacean"),
    
    /** Eggs and products thereof */
    EGG("Egg"),
    
    /** Fish and products thereof */
    FISH("Fish"),
    
    /** Peanuts and products thereof */
    PEANUT("Peanut"),
    
    /** Soybeans and products thereof */
    SOY("Soy"),
    
    /** Milk and products thereof (including lactose) */
    LACTOSE("Lactose"),
    
    /** Tree nuts (almonds, hazelnuts, walnuts, cashews, etc.) */
    NUTS("Nuts"),
    
    /** Celery and products thereof */
    CELERY("Celery"),
    
    /** Mustard and products thereof */
    MUSTARD("Mustard"),
    
    /** Sesame seeds and products thereof */
    SESAME("Sesame"),
    
    /** Sulphur dioxide and sulphites */
    SULFITE("Sulfite"),
    
    /** Lupin and products thereof */
    LUPIN("Lupin"),
    
    /** Molluscs and products thereof (e.g., mussels, clams, oysters) */
    MOLLUSK("Mollusk");

    /** Display name of the allergen */
    private final String allergen;

    /**
     * Constructs an AllergenEnum with a display name.
     * @param allergen The display name of the allergen
     */
    AllergenEnum(String allergen) {
        this.allergen = allergen;
    }

}
