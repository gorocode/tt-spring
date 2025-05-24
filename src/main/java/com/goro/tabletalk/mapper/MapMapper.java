package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.MapDTO;
import com.goro.tabletalk.entity.MapEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between MapDTO and MapEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface MapMapper {
    /**
     * Converts a MapEntity to a MapDTO.
     * @param mapEntity The MapEntity to convert
     * @return The converted MapDTO
     */
    MapDTO toMapDTO(MapEntity mapEntity);

    /**
     * Converts a list of MapEntity objects to a list of MapDTO objects.
     * @param mapEntity The list of MapEntity objects to convert
     * @return The list of converted MapDTO objects
     */
    List<MapDTO> toMapDTO(List<MapEntity> mapEntity);

    /**
     * Converts a MapDTO to a MapEntity.
     * @param map The MapDTO to convert
     * @return The converted MapEntity
     */
    MapEntity toMapEntity(MapDTO map);
}
