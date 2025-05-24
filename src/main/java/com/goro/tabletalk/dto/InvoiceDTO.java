package com.goro.tabletalk.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceDTO {

    private Long id;

    @Size(max = 250, message = "Customer name cannot contain more than 250 characters")
    private String customer_name;

    @Size(max = 10, message = "Customer id cannot contain more than 10 characters")
    private String customer_id;

    @Size(max = 500, message = "Address cannot contain more than 500 characters")
    private String address;

    @NotNull(message = "Date can't be null")
    private LocalDateTime date;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0", inclusive = true, message = "Price can't be less than 0")
    private BigDecimal paidWithCard;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0", inclusive = true, message = "Price can't be less than 0")
    private BigDecimal paidWithCash;

    @OneToMany(mappedBy = "invoice")
    private OrderDTO order;
}
