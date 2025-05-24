package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.OrderDTO;
import com.goro.tabletalk.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for converting between Order DTOs and entities.
 * Uses MapStruct for automatic implementation of mapping methods.
 * Handles both single orders and collections of orders.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
    /**
     * Converts an Order DTO to an entity.
     * @param order The Order DTO to convert
     * @return The converted Order entity
     */
    OrderEntity toOrderEntity(OrderDTO order);

    /**
     * Converts an Order entity to a DTO.
     * @param order The Order entity to convert
     * @return The converted Order DTO
     */
    OrderDTO toOrderResponse(OrderEntity order);

    /**
     * Converts a list of Order DTOs to entities.
     * @param orderList The list of Order DTOs to convert
     * @return The list of converted Order entities
     */
    List<OrderEntity> toOrderEntity(List<OrderDTO> orderList);

    /**
     * Converts a list of Order entities to DTOs.
     * @param orderList The list of Order entities to convert
     * @return The list of converted Order DTOs
     */
    List<OrderDTO> toOrderResponse(List<OrderEntity> orderList);

    /**
     * Updates an existing Order entity with data from a DTO.
     * The ID field is ignored to prevent overwriting the entity's identifier.
     * @param orderDTO The DTO containing the new data
     * @param orderEntity The entity to update
     */
    @Mapping(target = "id", ignore = true)
    void updateOrderFromDTO(OrderDTO orderDTO, @MappingTarget OrderEntity orderEntity);

}
