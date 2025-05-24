package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.MenuDTO;
import com.goro.tabletalk.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing menu operations.
 * Provides endpoints for creating, retrieving, updating, and managing menu availability.
 */
@RestController
@RequestMapping("/menu")
@Validated
public class MenuController {
    /** Service for handling menu-related operations */
    @Autowired
    private MenuService menuService;

    /**
     * Creates a new menu.
     * @param MenuDTO The menu data to create
     * @return ResponseEntity containing the created menu
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MenuDTO> createMenu(@Valid @RequestBody MenuDTO MenuDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuService.saveMenu(MenuDTO));
    }

    /**
     * Updates an existing menu.
     * @param menuDTO The menu data to update
     * @return ResponseEntity containing the updated menu
     */
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MenuDTO> updateMenu(@Valid @RequestBody MenuDTO menuDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuService.updateMenu(menuDTO));
    }

    /**
     * Toggles the availability status of a menu.
     * @param id The ID of the menu to toggle
     * @return ResponseEntity containing the updated menu
     */
    @PatchMapping("/{id}/toggle-available")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MenuDTO> toggleAvailableMenuItem(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuService.toggleAvailableMenu(id));
    }

    /**
     * Retrieves all menus.
     * @return ResponseEntity containing the list of all menus
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuService.findAllMenus());
    }

    /**
     * Retrieves the currently available menu.
     * @return ResponseEntity containing the available menu
     */
    @GetMapping("/available")
    // @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<MenuDTO> getAvailableMenu() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuService.findAvailableMenu());
    }

    /**
     * Retrieves a menu by its ID.
     * @param id The ID of the menu to retrieve
     * @return ResponseEntity containing the requested menu
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuService.findMenuById(id));
    }

    /**
     * Deletes a menu by its ID.
     * @param id The ID of the menu to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<SuccessResponse<String>> deleteMenuById(@PathVariable("id") Long id) {
        menuService.deleteMenuById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Menu deleted successfully"));
    }
}
