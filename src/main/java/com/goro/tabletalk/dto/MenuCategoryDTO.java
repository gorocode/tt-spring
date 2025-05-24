package com.goro.tabletalk.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuCategoryDTO {
    private Long id;

    private String name;

    private String description;

    private List<MenuItemSumDTO> menuItems;

    private Long menuOrder;
}
