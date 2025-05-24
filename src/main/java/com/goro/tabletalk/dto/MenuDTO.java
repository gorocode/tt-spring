package com.goro.tabletalk.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuDTO {
    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private List<MenuCategoryDTO> categories;
}
