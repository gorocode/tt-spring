package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MenuCategoryDTO;

import java.util.List;

/**
 * Service interface for managing menu categories.
 * Provides methods for creating, updating, retrieving, and deleting menu categories.
 */
public interface MenuCategoryService {
    /**
     * Creates a new menu category.
     * @param menuCategory The menu category data to save
     * @return The saved menu category
     */
    MenuCategoryDTO saveMenuCategory(MenuCategoryDTO menuCategory);

    /**
     * Updates an existing menu category.
     * @param menuCategory The menu category data to update
     * @return The updated menu category
     */
    MenuCategoryDTO updateMenuCategory(MenuCategoryDTO menuCategory);

    /**
     * Retrieves all menu categories.
     * @return List of all menu categories
     */
    List<MenuCategoryDTO> findAllCategories();

    /**
     * Deletes a menu category by its ID.
     * @param id The ID of the menu category to delete
     */
    void deleteMenuCategoryById(Long id);
}
