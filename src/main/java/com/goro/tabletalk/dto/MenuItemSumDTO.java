package com.goro.tabletalk.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemSumDTO {
    private Long id;

    private BigDecimal price;

    private BigDecimal tax;

    private Boolean available;

    private ProductDTO product;

    private Long menuOrder;
}
