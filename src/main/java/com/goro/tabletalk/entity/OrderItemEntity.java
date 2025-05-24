package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Entity representing an individual item within an order.
 * Contains information about the menu item ordered, quantity, price,
 * and its completion status.
 */
@Getter
@Setter
@Entity
public class OrderItemEntity {
    /** Unique identifier for the order item */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Quantity of the menu item ordered */
    @NotNull(message = "Quantity can't be null")
    @Min(value = 0, message = "Quantity can't be less than 0")
    private Integer quantity;

    /** Unit price of the menu item at the time of ordering */
    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", message = "Price can't be less than 0")
    private BigDecimal price;

    /** Tax amount for this order item */
    @DecimalMin(value = "0.0", message = "Tax can't be less than 0")
    private BigDecimal tax;

    /** Optional note or special instructions for this item */
    @Size(max = 250, message = "Note can't contain more than 250 characters")
    private String note;

    /** Reference to the menu item being ordered */
    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    @NotNull(message = "Order item must be associated with a menu item")
    private MenuItemEntity menuItem;

    /** Reference to the parent order */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull(message = "Order item must be associated with an order")
    private OrderEntity order;

    /** Flag indicating if the item has been prepared and served */
    @NotNull
    private Boolean completed = false;
}
