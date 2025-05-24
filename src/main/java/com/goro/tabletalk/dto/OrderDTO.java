package com.goro.tabletalk.dto;

import com.goro.tabletalk.enumeration.OrderStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;

    private BigDecimal totalWithoutTax;

    private BigDecimal totalWithTax;

    @NotNull(message = "Date can't be null")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    private Boolean paid;

    private List<OrderItemDTO> orderItems;

    @NotNull(message = "Order must be associated with a table")
    private TableDTO table;

}
