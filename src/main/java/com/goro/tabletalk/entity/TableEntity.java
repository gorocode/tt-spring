package com.goro.tabletalk.entity;

import com.goro.tabletalk.enumeration.TableLocationEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a restaurant table.
 * Contains information about table number, capacity, status, location,
 * and its relationships with orders and table maps.
 */
@Getter
@Setter
@Entity
public class TableEntity {
    /** Unique identifier for the table */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Table number in the restaurant */
    @NotNull(message = "Table number cannot be null.")
    @PositiveOrZero(message = "Table number must be zero or a positive integer.")
    private Integer number;

    /** Number of seats at the table */
    @NotNull(message = "Table capacity cannot be null.")
    @Min(value = 1, message = "Capacity must be at least 1.")
    private Integer capacity;

    /** Physical location of the table in the restaurant */
    @NotNull(message = "Table location cannot be null.")
    private TableLocationEnum location;

    /** Flag indicating if the table has been deleted */
    @NotNull(message = "Deleted flag cannot be null.")
    private Boolean deleted = false;

    /** List of orders associated with this table */
    @OneToMany(mappedBy = "table")
    private List<OrderEntity> orders;

    /** List of table map entries for this table */
    @OneToMany(mappedBy = "table")
    private List<TableMapEntity> tableMaps = new ArrayList<>();

}
