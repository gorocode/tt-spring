package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MapDTO;

import java.util.List;

/**
 * Service interface for managing restaurant floor maps.
 * Provides operations for creating, retrieving, updating, and deleting maps.
 */
public interface MapService {
    /**
     * Creates a new restaurant floor map.
     * @param name The name of the map to create
     * @return The created map
     */
    MapDTO createMap(String name);

    /**
     * Retrieves all restaurant floor maps.
     * @return List of all maps
     */
    List<MapDTO> getAllMaps();

    /**
     * Retrieves a specific map by its ID.
     * @param id The ID of the map to retrieve
     * @return The requested map
     */
    MapDTO getMap(Long id);

    /**
     * Updates an existing map.
     * @param map The map data to update
     * @return The updated map
     */
    MapDTO updateMap(MapDTO map);

    /**
     * Deletes a map by its ID.
     * @param id The ID of the map to delete
     */
    void deleteMap(Long id);
}
