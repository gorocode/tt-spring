package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.OrderItemDTO;
import com.goro.tabletalk.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between OrderItem DTOs and entities.
 * Uses MapStruct for automatic implementation of mapping methods.
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    /**
     * Updates an existing OrderItemEntity with data from a DTO.
     * The ID field is ignored to prevent overwriting the entity's identifier.
     *
     * @param dto The DTO containing the new data
     * @param entity The entity to update
     */
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(OrderItemDTO dto, @MappingTarget OrderItemEntity entity);
}
