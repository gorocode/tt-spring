package com.goro.tabletalk.dto;

import com.goro.tabletalk.enumeration.TableLocationEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableDTO {
    private Long id;

    @NotNull(message = "Table number cannot be null.")
    @PositiveOrZero(message = "Table number must be zero or a positive integer.")
    private Integer number;

    @NotNull(message = "Table capacity cannot be null.")
    @Min(value = 1, message = "Capacity must be at least 1.")
    private Integer capacity;

    @NotNull(message = "Table location cannot be null.")
    private TableLocationEnum location;

}
