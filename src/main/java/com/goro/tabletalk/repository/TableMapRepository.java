package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.TableMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link TableMapEntity} instances.
 * Provides standard JPA operations for table map layouts.
 */
@Repository
public interface TableMapRepository extends JpaRepository<TableMapEntity, Long> {
}
