package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.MenuCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link MenuCategoryEntity} instances.
 * Provides standard JPA operations and custom queries for menu categories.
 */
@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategoryEntity, Long> {
    /**
     * Finds a menu category by its name, ignoring case.
     * 
     * @param name The name of the category to find
     * @return Optional containing the found category, or empty if not found
     */
    Optional<MenuCategoryEntity> findByNameIgnoreCase(String name);

}
