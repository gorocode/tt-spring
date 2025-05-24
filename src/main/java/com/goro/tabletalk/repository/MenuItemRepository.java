package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link MenuItemEntity} instances.
 * Provides standard JPA operations and support for dynamic queries
 * through specifications.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long>, JpaSpecificationExecutor<MenuItemEntity> {
}
