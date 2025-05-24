package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.TableDTO;

import java.util.List;

/**
 * Service interface for managing restaurant tables.
 * Provides methods for creating, updating, retrieving, and deleting tables.
 */
public interface TableService {
    /**
     * Creates a new restaurant table.
     * @param table The table data to save
     * @return The saved table
     */
    TableDTO saveTable(TableDTO table);

    /**
     * Updates an existing restaurant table.
     * @param table The table data to update
     * @return The updated table
     */
    TableDTO updateTable(TableDTO table);

    /**
     * Retrieves all restaurant tables.
     * @return List of all tables
     */
    List<TableDTO> findAllTables();

    /**
     * Deletes a restaurant table by its ID.
     * @param id The ID of the table to delete
     */
    void deleteTableById(Long id);
}
