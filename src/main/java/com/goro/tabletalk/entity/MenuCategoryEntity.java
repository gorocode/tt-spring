package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a menu category in the restaurant menu.
 * Categories help organize menu items into logical groups like appetizers,
 * main courses, desserts, etc.
 */
@Getter
@Setter
@Entity
public class MenuCategoryEntity {
    /** Unique identifier for the menu category */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the menu category */
    @NotBlank(message = "Menu name cannot be blank")
    @Size(max = 100, message = "Menu name can't contain more than 100 characters")
    private String name;

    /** Optional description of the menu category */
    @Size(max = 500, message = "Menu description can't contain more than 500 characters")
    private String description;

    /** List of menu items in this category */
    @OneToMany(mappedBy = "category")
    private List<MenuItemEntity> menuItems;

    /** Display order within the menu */
    private Long menuOrder;
}
