package com.goro.tabletalk.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapDTO {
    private Long id;

    @NotNull(message = "Price can't be null")
    @Size(max = 25, message = "Name can't contain more than 25 characters")
    private String name;

    @OneToMany(mappedBy = "map")
    private List<TableMapDTO> tableMap;
}
