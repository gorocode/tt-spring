package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link ProductEntity} instances.
 * Provides standard JPA operations, support for specifications,
 * and custom queries for products.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    /**
     * Finds a product by its name, ignoring case.
     * 
     * @param name The name of the product to find
     * @return Optional containing the found product, or empty if not found
     */
    Optional<ProductEntity> findByNameIgnoreCase(String name);

    /**
     * Finds all products that are not deleted.
     * 
     * @return List of products that are not deleted
     */
    List<ProductEntity> findByDeletedFalse();
}
