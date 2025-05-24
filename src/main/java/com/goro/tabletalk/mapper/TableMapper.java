package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.TableDTO;
import com.goro.tabletalk.entity.TableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for converting between Table DTOs and Entities.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface TableMapper {
    /**
     * Converts a TableDTO to a TableEntity.
     * @param table The table DTO to convert
     * @return The converted table entity
     */
    TableEntity toTableEntity(TableDTO table);

    /**
     * Converts a TableEntity to a TableDTO.
     * @param table The table entity to convert
     * @return The converted table DTO
     */
    TableDTO toTableDTO(TableEntity table);

    /**
     * Converts a list of TableEntity objects to DTOs.
     * @param table The list of table entities to convert
     * @return The list of converted table DTOs
     */
    List<TableDTO> toTableDTO(List<TableEntity> table);

    /**
     * Updates a TableEntity with data from a TableDTO.
     * The ID field is ignored to prevent overwriting the existing ID.
     * @param tableDTO The table DTO containing the new data
     * @param tableEntity The table entity to update
     */
    @Mapping(target = "id", ignore = true)
    void updateTableEntityFromDTO(TableDTO tableDTO, @MappingTarget TableEntity tableEntity);

}
