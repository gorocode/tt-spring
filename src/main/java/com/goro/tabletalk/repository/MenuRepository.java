package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link MenuEntity} instances.
 * Provides standard JPA operations and custom queries for menus.
 */
@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    /**
     * Finds a menu by its name, ignoring case.
     * 
     * @param name The name of the menu to find
     * @return Optional containing the found menu, or empty if not found
     */
    Optional<MenuEntity> findByNameIgnoreCase(String name);

    /**
     * Finds the currently available menu.
     * 
     * @return Optional containing the available menu, or empty if none is available
     */
    Optional<MenuEntity> findByAvailableTrue();
}
