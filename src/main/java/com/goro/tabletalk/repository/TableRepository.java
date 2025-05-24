package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.TableEntity;
import com.goro.tabletalk.enumeration.TableLocationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link TableEntity} instances.
 * Provides standard JPA operations and custom queries for restaurant tables.
 */
@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    /**
     * Finds a table by its number and location.
     * 
     * @param number The table number
     * @param location The location of the table in the restaurant
     * @return Optional containing the found table, or empty if not found
     */
    Optional<TableEntity> findByNumberAndLocation(Integer number, TableLocationEnum location);
    
    /**
     * Retrieves all {@link TableEntity} instances that are not deleted.
     *
     * @return List of {@link TableEntity} instances that are not deleted.
     */
    List<TableEntity> findByDeletedFalse();
}
