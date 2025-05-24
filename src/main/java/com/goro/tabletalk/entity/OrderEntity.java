package com.goro.tabletalk.entity;

import com.goro.tabletalk.enumeration.OrderStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an order in the restaurant.
 * Contains information about order items, totals, status, and relationships
 * with tables and invoices.
 */
@Getter
@Setter
@Entity
public class OrderEntity {
    /** Unique identifier for the order */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Total amount of the order before taxes */
    @NotNull(message = "Total (without taxes) can't be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price (without taxes) can't be less than 0")
    private BigDecimal totalWithoutTax = BigDecimal.ZERO;

    /** Total amount of the order including taxes */
    @NotNull(message = "Total (with taxes) can't be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price (with taxes) can't be less than 0")
    private BigDecimal totalWithTax = BigDecimal.ZERO;

    /** Date and time when the order was created */
    @NotNull(message = "Date can't be null")
    private LocalDateTime date = LocalDateTime.now();

    /** Current status of the order (e.g., PENDING, COMPLETED) */
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status = OrderStatusEnum.PENDING;

    /** Flag indicating if the order has been paid */
    private Boolean paid = false;

    /** List of items in the order */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    /** Table associated with this order */
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    @NotNull(message = "Order must be associated with a table")
    private TableEntity table;

    /** Invoice generated for this order, if any */
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;
}
