package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MenuDTO;

import java.util.List;

/**
 * Service interface for managing menu operations.
 * Handles CRUD operations and availability management for menus.
 */
public interface MenuService {
    /**
     * Creates a new menu.
     * @param menu The menu data to save
     * @return The saved menu
     */
    MenuDTO saveMenu(MenuDTO menu);

    /**
     * Updates an existing menu.
     * @param menu The menu data to update
     * @return The updated menu
     */
    MenuDTO updateMenu(MenuDTO menu);

    /**
     * Toggles the availability status of a menu.
     * @param id The ID of the menu to toggle
     * @return The updated menu with new availability status
     */
    MenuDTO toggleAvailableMenu(Long id);

    /**
     * Finds the currently available menu.
     * @return The available menu
     */
    MenuDTO findAvailableMenu();

    /**
     * Retrieves all menus in the system.
     * @return List of all menus
     */
    List<MenuDTO> findAllMenus();

    /**
     * Finds a menu by its ID.
     * @param id The ID of the menu to find
     * @return The found menu
     */
    MenuDTO findMenuById(Long id);

    /**
     * Deletes a menu by its ID.
     * @param id The ID of the menu to delete
     */
    void deleteMenuById(Long id);
}
