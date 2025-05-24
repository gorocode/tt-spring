package com.goro.tabletalk.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal tax;

    private String note;

    private MenuItemSumDTO menuItem;

    private Boolean completed;
}
