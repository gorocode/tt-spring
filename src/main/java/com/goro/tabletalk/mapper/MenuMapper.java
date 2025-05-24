package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.MenuCategoryDTO;
import com.goro.tabletalk.dto.MenuDTO;
import com.goro.tabletalk.dto.MenuItemSumDTO;
import com.goro.tabletalk.entity.MenuCategoryEntity;
import com.goro.tabletalk.entity.MenuEntity;
import com.goro.tabletalk.entity.MenuItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper interface for converting between Menu DTOs and Entities.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface MenuMapper {
    /**
     * Converts a MenuDTO to a MenuEntity.
     * @param MenuDTO The menu DTO to convert
     * @return The converted menu entity
     */
    MenuEntity toEntity(MenuDTO MenuDTO);

    /**
     * Converts a MenuEntity to a MenuDTO.
     * Groups menu items by category during conversion.
     * @param menuEntity The menu entity to convert
     * @return The converted menu DTO
     */
    @Mapping(target = "categories", expression = "java(groupMenuItemsByCategory(menuEntity.getMenuItems()))")
    MenuDTO toDTO(MenuEntity menuEntity);

    /**
     * Converts a list of MenuEntity objects to a list of MenuDTO objects.
     * @param menuItemEntity The list of menu entities to convert
     * @return The list of converted menu DTOs
     */
    List<MenuDTO> toDTO(List<MenuEntity> menuItemEntity);

    /**
     * Groups menu items by their categories and creates category DTOs.
     * Only includes available menu items in the grouping.
     * @param menuItems The list of menu items to group
     * @return List of menu categories with their associated menu items
     */
    @Named("groupMenuItemsByCategory")
    default List<MenuCategoryDTO> groupMenuItemsByCategory(List<MenuItemEntity> menuItems) {
        return menuItems.stream()
                .filter(item -> !item.getDeleted())
                .collect(Collectors.groupingBy(MenuItemEntity::getCategory))
                .entrySet().stream()
                .map(entry -> {
                    MenuCategoryEntity category = entry.getKey();
                    List<MenuItemEntity> items = entry.getValue();

                    List<MenuItemSumDTO> itemDTOs = items.stream()
                            .map(this::toMenuItemSummarizedResponse)
                            .collect(Collectors.toList());

                    MenuCategoryDTO categoryDTO = new MenuCategoryDTO();
                    categoryDTO.setId(category.getId());
                    categoryDTO.setName(category.getName());
                    categoryDTO.setDescription(category.getDescription());
                    categoryDTO.setMenuItems(itemDTOs);
                    categoryDTO.setMenuOrder(category.getMenuOrder());

                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * Converts a MenuItemEntity to a summarized DTO representation.
     * @param menuItemEntity The menu item entity to convert
     * @return The summarized menu item DTO
     */
    MenuItemSumDTO toMenuItemSummarizedResponse(MenuItemEntity menuItemEntity);

}
