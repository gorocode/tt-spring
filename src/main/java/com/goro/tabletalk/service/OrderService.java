package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.OrderDTO;
import com.goro.tabletalk.enumeration.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface for managing order operations.
 * Handles order creation, updates, and retrieval with various filtering options.
 */
public interface OrderService {
    /**
     * Creates a new order for a specific table.
     * @param tableId The ID of the table to create the order for
     * @return The created order
     */
    OrderDTO saveOrder(Long tableId);

    /**
     * Creates a new order with full order details.
     * @param order The complete order data
     * @return The created order
     */
    OrderDTO saveFullOrder(OrderDTO order);

    /**
     * Retrieves all active orders in the system.
     * @return List of active orders
     */
    List<OrderDTO> findAllActiveOrders();

    /**
     * Finds the active order for a specific table.
     * @param id The ID of the table
     * @return The active order for the table
     */
    OrderDTO findActiveOrderByTableId(Long id);

    /**
     * Updates an existing order.
     * @param order The order data to update
     * @return The updated order
     */
    OrderDTO updateOrder(OrderDTO order);

    /**
     * Finds an order by its ID.
     * @param id The ID of the order to find
     * @return The found order
     */
    OrderDTO findById(Long id);

    /**
     * Retrieves orders based on various filter criteria.
     * @param orderId Optional order ID filter
     * @param tableId Optional table ID filter
     * @param status Optional list of order statuses to filter by
     * @param paid Optional payment status filter
     * @param startDate Optional start date filter
     * @param endDate Optional end date filter
     * @return List of orders matching the filter criteria
     */
    List<OrderDTO> getOrdersByFilters(Long orderId, Long tableId,
            List<OrderStatusEnum> status, Boolean paid,
            LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Deletes an order by its ID.
     * @param id The ID of the order to delete
     */
    void deleteOrderById(Long id);
}
