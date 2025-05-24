package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MenuCategoryDTO;
import com.goro.tabletalk.entity.MenuCategoryEntity;
import com.goro.tabletalk.mapper.MenuCategoryMapper;
import com.goro.tabletalk.repository.MenuCategoryRepository;
import com.goro.tabletalk.repository.MenuItemRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MenuCategoryServiceImp implements MenuCategoryService {

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private MenuCategoryMapper menuCategoryMapper;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuCategoryDTO saveMenuCategory(MenuCategoryDTO menuCategory) {
        if (menuCategoryRepository.findByNameIgnoreCase(menuCategory.getName()).isPresent()) {
            throw new NonUniqueResultException("Menu category with name " + menuCategory.getName() + " already exists");
        }
        return menuCategoryMapper.toDTO(
                menuCategoryRepository.save(menuCategoryMapper.toEntity(menuCategory)));
    }

    @Override
    public MenuCategoryDTO updateMenuCategory(MenuCategoryDTO menuCategory) {
        menuCategoryRepository.findById(menuCategory.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Menu category with id " + menuCategory.getId() + " not found"));
        Optional<MenuCategoryEntity> menuCategoryEntity = menuCategoryRepository.findByNameIgnoreCase(
                menuCategory.getName());

        if (menuCategoryEntity.isPresent() && !Objects.equals(menuCategoryEntity.get().getId(), menuCategory.getId())) {
            throw new NonUniqueResultException("Menu category with name " + menuCategory.getName() + " already exists");
        }
        return menuCategoryMapper.toDTO(
                menuCategoryRepository.save(menuCategoryMapper.toEntity(menuCategory)));
    }

    @Override
    public List<MenuCategoryDTO> findAllCategories() {
        return menuCategoryMapper.toDTO(
                menuCategoryRepository.findAll());
    }

    @Override
    public void deleteMenuCategoryById(Long id) {
        MenuCategoryEntity menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Menu category with id " + id + " not found"));
        menuCategory.getMenuItems().forEach(item -> {
            item.setCategory(null);
            item.setDeleted(true);
            item.setMenu(null);
        });
        menuItemRepository.saveAll(menuCategory.getMenuItems());
        menuCategory.setMenuItems(null);
        menuCategoryRepository.deleteById(id);
    }
}
