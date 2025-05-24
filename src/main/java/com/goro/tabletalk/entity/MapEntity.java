package com.goro.tabletalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a restaurant floor map.
 * Contains information about the map's name and associated table positions.
 */
@Entity
@Getter
@Setter
public class MapEntity {
    /** Unique identifier for the map */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the restaurant floor map */
    @NotNull(message = "Name can't be null")
    @Size(max = 25, message = "Name can't contain more than 25 characters")
    private String name;

    /** List of table positions on this map */
    @OneToMany(mappedBy = "map")
    private List<TableMapEntity> tableMap = new ArrayList<>();

}
