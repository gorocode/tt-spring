package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a table's position and properties on a restaurant map.
 * Contains information about the table's location, dimensions, appearance,
 * and relationships with the map and table entities.
 */
@Getter
@Setter
@Entity
public class TableMapEntity {
    /** Unique identifier for the table map entry */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Horizontal gap between table elements */
    private Double gapX;

    /** Vertical gap between table elements */
    private Double gapY;

    /** Rotation angle of the table in degrees */
    private Double angle;

    /** Shape of the table (e.g., 'rectangle', 'circle') */
    private String shape;

    /** Color used to display the table on the map */
    @NotBlank(message = "Color cannot be blank.")
    @Size(max = 20, message = "Color can have a maximum of 20 characters.")
    private String color;

    /** Height of the table in pixels */
    private Integer height;

    /** Width of the table in pixels */
    private Integer width;

    /** X-coordinate position on the map */
    @NotNull(message = "X coordinate cannot be null.")
    @Min(value = 0, message = "X coordinate must be at least 0.")
    private Integer x;

    /** Y-coordinate position on the map */
    @NotNull(message = "Y coordinate cannot be null.")
    @Min(value = 0, message = "Y coordinate must be at least 0.")
    private Integer y;

    /** Z-index for layering tables on the map */
    @NotNull(message = "Z coordinate cannot be null.")
    @Min(value = 0, message = "Z coordinate must be at least 0.")
    private Integer z;

    /** Reference to the map this table position belongs to */
    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private MapEntity map;

    /** Reference to the table being positioned */
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private TableEntity table;
}
