package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.MenuCategoryDTO;
import com.goro.tabletalk.dto.MenuDTO;
import com.goro.tabletalk.dto.MenuItemDTO;
import com.goro.tabletalk.dto.MenuItemSumDTO;
import com.goro.tabletalk.entity.MenuCategoryEntity;
import com.goro.tabletalk.entity.MenuEntity;
import com.goro.tabletalk.entity.MenuItemEntity;
import com.goro.tabletalk.mapper.MenuMapper;
import com.goro.tabletalk.repository.MenuCategoryRepository;
import com.goro.tabletalk.repository.MenuItemRepository;
import com.goro.tabletalk.repository.MenuRepository;
import com.goro.tabletalk.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public MenuDTO saveMenu(MenuDTO menu) {
        if (menuRepository.findByNameIgnoreCase(menu.getName()).isPresent()) {
            throw new NonUniqueResultException("Menu with name " + menu.getName() + " already exists");
        }
        return menuMapper.toDTO(menuRepository.save(menuMapper.toEntity(menu)));
    }

    @Transactional
    @Override
    public MenuDTO updateMenu(MenuDTO menu) {
        Optional<MenuEntity> menuByName =  menuRepository.findByNameIgnoreCase(menu.getName());
        if (menuByName.isPresent() && !Objects.equals(menuByName.get().getId(), menu.getId())) {
            throw new NonUniqueResultException("Menu with name " + menu.getName() + " already exists");
        }

        MenuEntity menuEntity =
                menuRepository.findById(menu.getId())
                        .orElseThrow(
                                () -> new EntityNotFoundException("Menu with id " + menu.getId() + " not found"));

        menuEntity.setName(menu.getName());
        menuEntity.setDescription(menu.getDescription());

        Map<Long, MenuItemEntity> existingItemsMap = menuEntity.getMenuItems().stream()
                .collect(Collectors.toMap(MenuItemEntity::getId, Function.identity()));

        menuEntity.getMenuItems().clear();

        List<MenuItemEntity> updatedMenuItems = new ArrayList<>();
        Set<Long> updatedItemIds = new HashSet<>();

        for (MenuCategoryDTO categoryDTO : menu.getCategories()) {
            MenuCategoryEntity categoryEntity = menuCategoryRepository.findById(categoryDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryDTO.getId() + " not found"));
            
            if (!Objects.equals(categoryEntity.getMenuOrder(), categoryDTO.getMenuOrder())) {
                categoryEntity.setMenuOrder(categoryDTO.getMenuOrder());
                menuCategoryRepository.save(categoryEntity);
            }

            for (MenuItemSumDTO itemDTO : categoryDTO.getMenuItems()) {
                MenuItemEntity itemEntity;

                if (itemDTO.getId() != null && existingItemsMap.containsKey(itemDTO.getId())) {
                    itemEntity = existingItemsMap.get(itemDTO.getId());
                    updatedItemIds.add(itemEntity.getId());
                } else {
                    itemEntity = new MenuItemEntity();
                    itemEntity.setMenu(menuEntity);
                    itemEntity.setProduct(productRepository.findById(itemDTO.getProduct().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Product with id " + itemDTO.getProduct().getId() + " not found")));
                    itemEntity.setPrice(itemDTO.getPrice());
                    itemEntity.setTax(itemDTO.getTax());
                }

                itemEntity.setCategory(categoryEntity);
                itemEntity.setAvailable(itemDTO.getAvailable());
                itemEntity.setMenuOrder(itemDTO.getMenuOrder());

                updatedMenuItems.add(itemEntity);
            }
        }

        for (MenuItemEntity existingItem : existingItemsMap.values()) {
            if (!updatedItemIds.contains(existingItem.getId())) {
                System.out.println(existingItem.getId());
                if (existingItem.getOrderItems().isEmpty()) {
                    menuItemRepository.deleteById(existingItem.getId());
                } else {
                    existingItem.setCategory(null);
                    existingItem.setMenu(null);
                    existingItem.setDeleted(true);
                    updatedMenuItems.add(existingItem);
                }
            }
        }

        menuEntity.getMenuItems().addAll(updatedMenuItems);

        menuItemRepository.saveAll(updatedMenuItems);
        menuRepository.save(menuEntity);

        return menuMapper.toDTO(menuEntity);
    }

    @Override
    public MenuDTO toggleAvailableMenu(Long id) {
        MenuEntity menuEntity = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Menu with id " + id + " not found"));
        if (!menuEntity.getAvailable()) {
                Optional<MenuEntity> activeMenu = menuRepository.findByAvailableTrue();
                if (activeMenu.isPresent()) {
                    activeMenu.get().setAvailable(false);
                    menuRepository.save(activeMenu.get());
                }
        }
        menuEntity.setAvailable(!menuEntity.getAvailable());
        return menuMapper.toDTO(menuRepository.save(menuEntity));
    }

    @Override
    public MenuDTO findAvailableMenu() {
        return menuMapper.toDTO(
                menuRepository.findByAvailableTrue()
                        .orElseThrow(() -> new EntityNotFoundException("There is no available menu")));
    }

    @Override
    public List<MenuDTO> findAllMenus() {
        return menuMapper.toDTO(menuRepository.findAll());
    }

    @Override
    public MenuDTO findMenuById(Long id) {
        return menuMapper.toDTO(
                menuRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Menu with id " + id + " not found")));
    }

    @Override
    public void deleteMenuById(Long id) {
        MenuEntity menuEntity = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + id + " not found"));
        menuEntity.getMenuItems().forEach(item -> {
            item.setMenu(null);
            item.setCategory(null);
            item.setDeleted(true);
        });
        menuItemRepository.saveAll(menuEntity.getMenuItems());
        menuEntity.setMenuItems(null);
        menuRepository.deleteById(id);
    }
}
