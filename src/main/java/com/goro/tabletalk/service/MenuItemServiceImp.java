package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MenuItemDTO;
import com.goro.tabletalk.dto.MenuItemSumDTO;
import com.goro.tabletalk.entity.MenuItemEntity;
import com.goro.tabletalk.mapper.MenuItemMapper;
import com.goro.tabletalk.repository.MenuCategoryRepository;
import com.goro.tabletalk.repository.MenuItemRepository;
import com.goro.tabletalk.repository.MenuRepository;
import com.goro.tabletalk.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.goro.tabletalk.repository.spec.MenuItemSpecs.*;

@Service
public class MenuItemServiceImp implements MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private MenuItemMapper menuItemMapper;

    @Override
    public MenuItemSumDTO saveMenuItem(MenuItemDTO menuItem) {
        MenuItemEntity menuItemEntity = menuItemMapper.toEntity(menuItem);

        menuItemEntity.setMenu(
                menuRepository.findById(menuItem.getMenuId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Menu with id " + menuItem.getMenuId() + " not found")));

        menuItemEntity.setProduct(
                productRepository.findById(menuItem.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Product with id " + menuItem.getProductId() + " not found"))
        );

        if (menuItem.getCategoryId() == null) {
            menuItem.setCategoryId(0L);
        }

        menuItemEntity.setCategory(
                menuCategoryRepository.findById(menuItem.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Category with id " + menuItem.getCategoryId() + " not found"))
        );

        return menuItemMapper.toDTO(menuItemRepository.save(menuItemEntity));
    }

    @Override
    public MenuItemSumDTO updateMenuItem(MenuItemDTO menuItem) {
        MenuItemEntity menuItemEntity =
                menuItemRepository.findById(menuItem.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Menu item with id " + menuItem.getId() + " not found"));

        menuItemEntity.setPrice(menuItem.getPrice());

        menuItemEntity.setTax(menuItem.getTax());

        if (menuItem.getCategoryId() != null) {
            menuItemEntity.setCategory(
                    menuCategoryRepository.findById(menuItem.getCategoryId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Category with id " + menuItem.getCategoryId() + " not found"))
            );
        }
        return menuItemMapper.toDTO(menuItemRepository.save(menuItemEntity));
    }

    @Override
    public MenuItemSumDTO toggleAvailableMenuItem(Long id) {
        MenuItemEntity menuItemEntity = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Menu item with id " + id + " not found"));
        menuItemEntity.setAvailable(!menuItemEntity.getAvailable());
        return menuItemMapper.toDTO(menuItemRepository.save(menuItemEntity));
    }

    @Override
    public List<MenuItemSumDTO> findAllMenuItems() {
        return menuItemMapper.toDTO(menuItemRepository.findAll());
    }

    @Override
    public MenuItemSumDTO findMenuItemById(Long id) {
        return menuItemMapper.toDTO(
                menuItemRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Menu item with id " + id + " not found")));
    }

    @Override
    public List<MenuItemSumDTO> findMenuItemsByMenuId(Long menuId) {
        menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + menuId + " not found"));
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll(inMenu(menuId));
        return menuItemMapper.toDTO(menuItemEntities);
    }

    @Override
    public List<MenuItemSumDTO> findMenuItemsByCategoryId(Long categoryId) {
        menuCategoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Category with id " + categoryId + " not found"));
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll(inCategory(categoryId));
        return menuItemMapper.toDTO(menuItemEntities);
    }

    @Override
    public List<MenuItemSumDTO> findMenuItemsByMenuIdAndCategoryId(Long menuId, Long categoryId) {
        menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + menuId + " not found"));
        menuCategoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Category with id " + categoryId + " not found"));
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll(inMenu(menuId).and(inCategory(categoryId)));
        return menuItemMapper.toDTO(menuItemEntities);
    }

    @Override
    public List<MenuItemSumDTO> findMenuItemsByMenuIdAndProductName(Long menuId, String productName) {
        menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + menuId + " not found"));
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll(inMenu(menuId).and(hasProduct(productName)));
        return menuItemMapper.toDTO(menuItemEntities);
    }

    @Override
    public List<MenuItemSumDTO> findMenuItemsByProductName(String productName) {
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll(hasProduct(productName));
        return menuItemMapper.toDTO(menuItemEntities);
    }

    @Override
    public void deleteMenuItemById(Long id) {
        menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu item with id " + id + " not found"));
        menuItemRepository.deleteById(id);
    }

}
