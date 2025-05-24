package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.MenuItemDTO;
import com.goro.tabletalk.dto.MenuItemSumDTO;
import com.goro.tabletalk.entity.MenuItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between MenuItem DTOs and Entities.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    /**
     * Converts a MenuItemDTO to a MenuItemEntity.
     * @param menuItem The menu item DTO to convert
     * @return The converted menu item entity
     */
    MenuItemEntity toEntity(MenuItemDTO menuItem);

    /**
     * Converts a MenuItemEntity to a summarized DTO.
     * @param menuItem The menu item entity to convert
     * @return The converted menu item DTO in summarized form
     */
    MenuItemSumDTO toDTO(MenuItemEntity menuItem);

    /**
     * Converts a list of MenuItemEntity objects to summarized DTOs.
     * @param menuItem The list of menu item entities to convert
     * @return The list of converted menu item DTOs in summarized form
     */
    List<MenuItemSumDTO> toDTO(List<MenuItemEntity> menuItem);
}
