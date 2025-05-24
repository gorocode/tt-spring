package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing an invoice in the system.
 * Contains customer information, payment details, and associated order.
 */
@Getter
@Setter
@Entity
public class InvoiceEntity {
    /** Unique identifier for the invoice */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the customer */
    @Size(max = 250, message = "Customer name cannot contain more than 250 characters")
    private String customer_name;

    /** Customer's identification number */
    @Size(max = 10, message = "Customer id cannot contain more than 10 characters")
    private String customer_id;

    /** Customer's billing address */
    @Size(max = 500, message = "Address cannot contain more than 500 characters")
    private String address;

    /** Date and time when the invoice was created */
    @NotNull(message = "Date can't be null")
    private LocalDateTime date;

    /** Amount paid using card payment */
    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price can't be less than 0")
    private BigDecimal paidWithCard;

    /** Amount paid using cash payment */
    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price can't be less than 0")
    private BigDecimal paidWithCash;

    /** Associated order for this invoice */
    @OneToOne(mappedBy = "invoice", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private OrderEntity order;
}
