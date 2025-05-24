package com.goro.tabletalk.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemDTO {
    @NotNull
    private Long id;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price can't be less than 0")
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "Tax can't be less than 0")
    private BigDecimal tax;

    private Boolean available;

    @NotNull(message = "Menu item must be associated with a product")
    private Long productId;

    private Long categoryId;

    @NotNull(message = "Menu item must be associated with a menu")
    private Long menuId;

}
