package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.MenuItemDTO;
import com.goro.tabletalk.dto.MenuItemSumDTO;
import com.goro.tabletalk.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing menu items.
 * Provides endpoints for creating, retrieving, updating, and managing menu
 * items,
 * including searching by various criteria such as menu, category, and product
 * name.
 */
@RestController
@RequestMapping("/menu-items")
@Validated
public class MenuItemController {
    /** Service for handling menu item operations */
    @Autowired
    private MenuItemService menuItemService;

    /**
     * Creates a new menu item.
     * 
     * @param menuItemDTO The menu item data to create
     * @return ResponseEntity containing the created menu item in summarized form
     */
    @PostMapping
    public ResponseEntity<MenuItemSumDTO> createMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuItemService.saveMenuItem(menuItemDTO));
    }

    /**
     * Updates an existing menu item.
     * 
     * @param menuItemDTO The menu item data to update
     * @return ResponseEntity containing the updated menu item in summarized form
     */
    @PutMapping
    public ResponseEntity<MenuItemSumDTO> updateMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.updateMenuItem(menuItemDTO));
    }

    /**
     * Toggles the availability status of a menu item.
     * 
     * @param id The ID of the menu item to toggle
     * @return ResponseEntity containing the updated menu item in summarized form
     */
    @PatchMapping("/{id}/toggle-available")
    public ResponseEntity<MenuItemSumDTO> toggleAvailableMenuItem(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.toggleAvailableMenuItem(id));
    }

    /**
     * Retrieves all menu items.
     * 
     * @return ResponseEntity containing the list of all menu items in summarized
     *         form
     */
    @GetMapping
    public ResponseEntity<List<MenuItemSumDTO>> getAllMenuItems() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findAllMenuItems());
    }

    /**
     * Retrieves a menu item by its ID.
     * 
     * @param id The ID of the menu item to retrieve
     * @return ResponseEntity containing the requested menu item in summarized form
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemSumDTO> getMenuItemById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findMenuItemById(id));
    }

    /**
     * Retrieves all menu items in a specific menu.
     * 
     * @param menuId The ID of the menu to find items for
     * @return ResponseEntity containing the list of menu items in summarized form
     */
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<MenuItemSumDTO>> getMenuItemsByMenuId(
            @PathVariable("menuId") Long menuId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findMenuItemsByMenuId(menuId));
    }

    /**
     * Retrieves all menu items in a specific category.
     * 
     * @param categoryId The ID of the category to find items for
     * @return ResponseEntity containing the list of menu items in summarized form
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<MenuItemSumDTO>> getMenuItemsByCategoryId(
            @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findMenuItemsByCategoryId(categoryId));
    }

    /**
     * Retrieves all menu items in a specific menu and category.
     * 
     * @param menuId     The ID of the menu to find items for
     * @param categoryId The ID of the category to find items for
     * @return ResponseEntity containing the list of menu items in summarized form
     */
    @GetMapping("/menu/{menuId}/category/{categoryId}")
    public ResponseEntity<List<MenuItemSumDTO>> getMenuItemsByMenuIdAndCategoryId(
            @PathVariable("menuId") Long menuId, @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findMenuItemsByMenuIdAndCategoryId(menuId, categoryId));
    }

    /**
     * Searches for menu items in a specific menu by product name.
     * 
     * @param menuId      The ID of the menu to search in
     * @param productName The product name to search for
     * @return ResponseEntity containing the list of matching menu items in
     *         summarized form
     */
    @GetMapping("/menu/{menuId}/product")
    public ResponseEntity<List<MenuItemSumDTO>> getMenuItemsByMenuIdAndProductName(
            @PathVariable("menuId") Long menuId, @RequestParam("productName") String productName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findMenuItemsByMenuIdAndProductName(menuId, productName));
    }

    /**
     * Searches for menu items by product name across all menus.
     * 
     * @param productName The product name to search for
     * @return ResponseEntity containing the list of matching menu items in
     *         summarized form
     */
    @GetMapping("/product")
    public ResponseEntity<List<MenuItemSumDTO>> getMenuItemsByMenuIdAndProductName(
            @RequestParam("productName") String productName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuItemService.findMenuItemsByProductName(productName));
    }

    /**
     * Deletes a menu item by its ID.
     * 
     * @param id The ID of the menu item to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<SuccessResponse<String>> deleteMenuItemById(@PathVariable("id") Long id) {
        menuItemService.deleteMenuItemById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Product deleted successfully"));
    }
}
