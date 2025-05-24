package com.goro.tabletalk.entity;

import com.goro.tabletalk.enumeration.AllergenEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a product in the restaurant menu.
 * Contains product details including name, description, stock level,
 * and allergen information.
 */
@Getter
@Setter
@Entity
public class ProductEntity {
    /** Unique identifier for the product */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the product, must be unique */
    @Column(unique = true)
    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Product name can't contain more than 100 characters")
    private String name;

    /** Detailed description of the product */
    @Size(max = 500, message = "Product description can't contain more than 500 characters")
    private String description;

    /** URL to the product's image */
    private String url;

    /** Current stock level of the product */
    @NotNull(message = "Stock can't be null")
    @Min(value = 0, message = "Stock can't be less than 0")
    private Integer stock;

    /** Flag indicating if the product has been deleted */
    @NotNull(message = "Deleted flag can't be null")
    private Boolean deleted = false;

    /** List of allergens present in the product */
    @ElementCollection(targetClass = AllergenEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_allergens", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "allergen")
    @Enumerated(EnumType.STRING)
    private List<AllergenEnum> allergens = new ArrayList<>();

    /** List of menu items using this product */
    @OneToMany(mappedBy = "product")
    private List<MenuItemEntity> menuItems = new ArrayList<>();

}
