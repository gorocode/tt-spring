package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link MapEntity} instances.
 * Provides standard JPA operations for restaurant map layouts.
 */
@Repository
public interface MapRepository extends JpaRepository<MapEntity, Long> {
}
