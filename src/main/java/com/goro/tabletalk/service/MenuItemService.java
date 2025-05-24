package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MenuItemDTO;
import com.goro.tabletalk.dto.MenuItemSumDTO;

import java.util.List;

/**
 * Service interface for managing menu items.
 * Provides operations for creating, retrieving, updating, and deleting menu items,
 * as well as managing their availability and searching by various criteria.
 */
public interface MenuItemService {
    /**
     * Creates a new menu item.
     * @param menuItem The menu item data to save
     * @return The created menu item in summarized form
     */
    MenuItemSumDTO saveMenuItem(MenuItemDTO menuItem);

    /**
     * Updates an existing menu item.
     * @param menuItem The menu item data to update
     * @return The updated menu item in summarized form
     */
    MenuItemSumDTO updateMenuItem(MenuItemDTO menuItem);

    /**
     * Toggles the availability status of a menu item.
     * @param id The ID of the menu item to toggle
     * @return The updated menu item in summarized form
     */
    MenuItemSumDTO toggleAvailableMenuItem(Long id);

    /**
     * Retrieves all menu items.
     * @return List of all menu items in summarized form
     */
    List<MenuItemSumDTO> findAllMenuItems();

    /**
     * Retrieves a specific menu item by its ID.
     * @param id The ID of the menu item to retrieve
     * @return The requested menu item in summarized form
     */
    MenuItemSumDTO findMenuItemById(Long id);

    /**
     * Retrieves all menu items associated with a specific menu.
     * @param menuId The ID of the menu to find items for
     * @return List of menu items in summarized form
     */
    List<MenuItemSumDTO> findMenuItemsByMenuId(Long menuId);

    /**
     * Retrieves all menu items in a specific category.
     * @param categoryId The ID of the category to find items for
     * @return List of menu items in summarized form
     */
    List<MenuItemSumDTO> findMenuItemsByCategoryId(Long categoryId);

    /**
     * Retrieves all menu items in a specific menu and category.
     * @param menuId The ID of the menu to find items for
     * @param categoryId The ID of the category to find items for
     * @return List of menu items in summarized form
     */
    List<MenuItemSumDTO> findMenuItemsByMenuIdAndCategoryId(Long menuId, Long categoryId);

    /**
     * Searches for menu items in a specific menu by product name.
     * @param menuId The ID of the menu to search in
     * @param productName The product name to search for
     * @return List of matching menu items in summarized form
     */
    List<MenuItemSumDTO> findMenuItemsByMenuIdAndProductName(Long menuId, String productName);

    /**
     * Searches for menu items by product name across all menus.
     * @param productName The product name to search for
     * @return List of matching menu items in summarized form
     */
    List<MenuItemSumDTO> findMenuItemsByProductName(String productName);

    /**
     * Deletes a menu item by its ID.
     * @param id The ID of the menu item to delete
     */
    void deleteMenuItemById(Long id);
}
