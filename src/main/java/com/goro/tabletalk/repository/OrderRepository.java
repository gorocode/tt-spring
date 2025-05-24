package com.goro.tabletalk.repository;

import com.goro.tabletalk.entity.OrderEntity;
import com.goro.tabletalk.enumeration.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Repository interface for managing {@link OrderEntity} instances.
 * Provides standard JPA operations, support for specifications,
 * and custom queries for orders.
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    /**
     * Finds all orders with a specific status.
     * 
     * @param status The status to filter by
     * @return List of orders with the specified status
     */
    List<OrderEntity> findByStatus(OrderStatusEnum status);

    /**
     * Finds all orders for a specific table with a specific status.
     * 
     * @param id The ID of the table
     * @param status The status to filter by
     * @return List of orders for the table with the specified status
     */
    List<OrderEntity> findByTableIdAndStatus(Long id, OrderStatusEnum status);

}
