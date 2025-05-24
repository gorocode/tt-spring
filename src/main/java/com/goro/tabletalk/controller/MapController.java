package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.MapDTO;
import com.goro.tabletalk.service.MapService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing restaurant floor map operations.
 * Provides endpoints for creating, retrieving, updating, and deleting maps.
 */
@RestController
@RequestMapping("/map")
@Validated
public class MapController {

    /** Service for handling map-related operations */
    @Autowired
    private MapService mapService;

    /**
     * Creates a new restaurant floor map.
     * @param name The name of the map to create
     * @return ResponseEntity containing the created map
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MapDTO> createMap(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapService.createMap(name));
    }

    /**
     * Retrieves all restaurant floor maps.
     * @return ResponseEntity containing the list of all maps
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<List<MapDTO>> getAllMaps() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapService.getAllMaps());
    }

    /**
     * Retrieves a specific map by its ID.
     * @param id The ID of the map to retrieve
     * @return ResponseEntity containing the requested map
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<MapDTO> getMapById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapService.getMap(id));
    }

    /**
     * Updates an existing map.
     * @param map The map data to update
     * @return ResponseEntity containing the updated map
     */
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<MapDTO> putMap(@Valid @RequestBody MapDTO map) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapService.updateMap(map));
    }

    /**
     * Deletes a map by its ID.
     * @param id The ID of the map to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<SuccessResponse<String>> deleteMap(@PathVariable Long id) {
        mapService.deleteMap(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Map deleted successfully"));
    }
}
