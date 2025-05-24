package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entity representing a menu item in the restaurant menu.
 * Contains pricing, availability, and relationships with product, category, and menu.
 */
@Getter
@Setter
@Entity
public class MenuItemEntity {
    /** Unique identifier for the menu item */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Price of the menu item */
    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price can't be less than 0")
    private BigDecimal price;

    /** Tax amount for the menu item */
    @DecimalMin(value = "0.0", message = "Tax can't be less than 0")
    private BigDecimal tax;

    /** Flag indicating if the menu item is currently available */
    @NotNull(message = "Availability can't be null")
    private Boolean available = true;

    /** Flag indicating if the menu item has been deleted */
    @NotNull(message = "Deleted cannot be null")
    private Boolean deleted = false;

    /** Associated product details */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull(message = "Menu item must be associated with a product")
    private ProductEntity product;

    /** Category of the menu item */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private MenuCategoryEntity category;

    /** Menu containing this item */
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = true)
    private MenuEntity menu;

    /** List of order items associated with this menu item */
    @OneToMany(mappedBy = "menuItem")
    private List<OrderItemEntity> orderItems;

    /** Display order within the menu */
    private Long menuOrder;
}
