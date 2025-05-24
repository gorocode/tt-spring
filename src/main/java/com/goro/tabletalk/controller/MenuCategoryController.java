package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.MenuCategoryDTO;
import com.goro.tabletalk.service.MenuCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing menu categories.
 * Provides endpoints for creating, updating, retrieving, and deleting menu categories.
 */
@RestController
@RequestMapping("/menu-category")
@Validated
public class MenuCategoryController {
    /** Service for handling menu category operations */
    @Autowired
    private MenuCategoryService menuCategoryService;

    /**
     * Creates a new menu category.
     * @param menuDTO The menu category data to create
     * @return ResponseEntity containing the created menu category
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MenuCategoryDTO> createMenuCategory(
            @Valid @RequestBody MenuCategoryDTO menuDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuCategoryService.saveMenuCategory(menuDTO));
    }

    /**
     * Updates an existing menu category.
     * @param menuCategoryDTO The menu category data to update
     * @return ResponseEntity containing the updated menu category
     */
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MenuCategoryDTO> updateMenuCategory(
            @Valid @RequestBody MenuCategoryDTO menuCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuCategoryService.updateMenuCategory(menuCategoryDTO));
    }

    /**
     * Retrieves all menu categories.
     * @return ResponseEntity containing the list of all menu categories
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<List<MenuCategoryDTO>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuCategoryService.findAllCategories());
    }

    /**
     * Deletes a menu category by its ID.
     * @param id The ID of the menu category to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<SuccessResponse<String>> deleteMenuCategoryId(@PathVariable("id") Long id) {
        menuCategoryService.deleteMenuCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Menu category deleted successfully"));
    }
}
