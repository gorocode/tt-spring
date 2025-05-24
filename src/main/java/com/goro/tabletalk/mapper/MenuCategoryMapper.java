package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.MenuCategoryDTO;
import com.goro.tabletalk.entity.MenuCategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between MenuCategory DTOs and Entities.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {
    /**
     * Converts a MenuCategoryDTO to a MenuCategoryEntity.
     * @param menuCategoryDTO The menu category DTO to convert
     * @return The converted menu category entity
     */
    MenuCategoryEntity toEntity(MenuCategoryDTO menuCategoryDTO);

    /**
     * Converts a MenuCategoryEntity to a MenuCategoryDTO.
     * @param menuCategoryEntity The menu category entity to convert
     * @return The converted menu category DTO
     */
    MenuCategoryDTO toDTO(MenuCategoryEntity menuCategoryEntity);

    /**
     * Converts a list of MenuCategoryEntity objects to DTOs.
     * @param menuCategories The list of menu category entities to convert
     * @return The list of converted menu category DTOs
     */
    List<MenuCategoryDTO> toDTO(List<MenuCategoryEntity> menuCategories);
}
