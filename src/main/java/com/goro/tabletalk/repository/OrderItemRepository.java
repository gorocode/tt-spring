package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link OrderItemEntity} instances.
 * Provides standard JPA operations and custom queries for order items.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    /**
     * Finds an order item by its order ID and menu item ID combination.
     * 
     * @param orderId The ID of the order
     * @param menuItemId The ID of the menu item
     * @return Optional containing the found order item, or empty if not found
     */
    Optional<OrderItemEntity> findByOrderIdAndMenuItemId(Long orderId, Long menuItemId);
}
