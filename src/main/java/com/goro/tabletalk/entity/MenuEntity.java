package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a restaurant menu.
 * Contains menu details and its associated menu items.
 */
@Getter
@Setter
@Entity
public class MenuEntity {
    /** Unique identifier for the menu */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the menu */
    @NotBlank(message = "Menu name cannot be blank")
    @Size(max = 100, message = "Product name can't contain more than 100 characters")
    private String name;

    /** Optional description of the menu */
    @Size(max = 500, message = "Menu description can't contain more than 500 characters")
    private String description;

    /** Flag indicating if the menu is currently available */
    @NotNull(message = "Availability can't be null")
    private Boolean available = false;

    /** List of menu items associated with this menu */
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemEntity> menuItems = new ArrayList<>();

}
