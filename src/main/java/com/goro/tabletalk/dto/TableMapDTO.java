package com.goro.tabletalk.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableMapDTO {
    private Long id;

    private Double gapX;

    private Double gapY;

    private Double angle;

    private String shape;

    @NotBlank(message = "Color cannot be blank.")
    @Size(max = 20, message = "Color can have a maximum of 20 characters.")
    private String color;

    private Integer height;

    private Integer width;

    @NotNull(message = "X coordinate cannot be null.")
    @Min(value = 0, message = "X coordinate must be at least 0.")
    private Integer x;

    @NotNull(message = "Y coordinate cannot be null.")
    @Min(value = 0, message = "Y coordinate must be at least 0.")
    private Integer y;

    @NotNull(message = "Z coordinate cannot be null.")
    @Min(value = 0, message = "Z coordinate must be at least 0.")
    private Integer z;

    private TableDTO table;
}
