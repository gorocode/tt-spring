package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.TableDTO;
import com.goro.tabletalk.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing restaurant tables.
 * Provides endpoints for creating, updating, retrieving, and deleting tables.
 */
@RestController
@RequestMapping("/tables")
@Validated
public class TableController {
    /** Service for handling table-related operations */
    @Autowired
    private TableService tableService;

    /**
     * Creates a new restaurant table.
     * @param table The table data to create
     * @return ResponseEntity containing the created table
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<TableDTO> createTable(@Valid @RequestBody TableDTO table) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tableService.saveTable(table));
    }

    /**
     * Updates an existing restaurant table.
     * @param table The table data to update
     * @return ResponseEntity containing the updated table
     */
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<TableDTO> putTableById(@Valid @RequestBody TableDTO table) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tableService.updateTable(table));
    }

    /**
     * Retrieves all restaurant tables.
     * @return ResponseEntity containing the list of all tables
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<List<TableDTO>> getAllTables() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tableService.findAllTables());
    }

    /**
     * Deletes a restaurant table by its ID.
     * @param id The ID of the table to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<SuccessResponse<String>> deleteTableById(@PathVariable("id") Long id) {
        tableService.deleteTableById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Table deleted successfully"));
    }
}
