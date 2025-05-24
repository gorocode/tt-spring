package com.goro.tabletalk.dto;

import com.goro.tabletalk.enumeration.AllergenEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    @NotNull
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Product name can't contain more than 100 characters")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    @Size(max = 500, message = "Product description can't contain more than 500 characters")
    private String description;

    @URL
    private String url;

    @NotNull
    @Min(value = 0, message = "Stock cannot be less than 0")
    private Integer stock;

    private List<AllergenEnum> allergens = new ArrayList<>();

}