package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.OrderDTO;
import com.goro.tabletalk.enumeration.OrderStatusEnum;
import com.goro.tabletalk.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for managing orders.
 * Provides endpoints for creating, updating, retrieving, and deleting orders.
 * Integrates with WebSocket for real-time updates to tables and kitchen display.
 */
@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {
    /** Service for handling order-related operations */
    @Autowired
    private OrderService orderService;

    /** Controller for sending real-time updates via WebSocket */
    @Autowired
    private WebSocketController webSocketController;

    /**
     * Creates a new empty order for a table.
     * @param tableId ID of the table to create the order for
     * @return ResponseEntity containing the created order
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<OrderDTO> createOrder(Long tableId) {
        OrderDTO order = orderService.saveOrder(tableId);
        webSocketController.updateTableOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /**
     * Creates a new order with items.
     * @param order The order data to create
     * @return ResponseEntity containing the created order
     */
    @PostMapping("/full")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<OrderDTO> createFullOrder(@RequestBody @Valid OrderDTO order) {
        OrderDTO orderEntity = orderService.saveFullOrder(order);
        webSocketController.updateTableOrder(orderEntity);
        webSocketController.updateKitchen(orderService.findAllActiveOrders());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderEntity);
    }

    /**
     * Retrieves orders based on various filter criteria.
     * @param orderId Optional order ID filter
     * @param tableId Optional table ID filter
     * @param status Optional list of order statuses to filter by
     * @param paid Optional payment status filter
     * @param startDate Optional start date filter
     * @param endDate Optional end date filter
     * @return ResponseEntity containing the filtered list of orders
     */
    @GetMapping("/filters")
    // @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<List<OrderDTO>> getOrdersByFilters(
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) Long tableId,
            @RequestParam(required = false) List<OrderStatusEnum> status,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrdersByFilters(orderId, tableId, status, paid, startDate, endDate));
    }

    /**
     * Updates an existing order.
     * @param order The order data to update
     * @return ResponseEntity containing the updated order
     */
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<OrderDTO> updateOrder(
            @Valid @RequestBody OrderDTO order) {
        OrderDTO orderDTO = orderService.updateOrder(order);
        webSocketController.updateTableOrder(orderDTO);
        webSocketController.updateKitchen(orderService.findAllActiveOrders());
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    /**
     * Deletes an order by its ID.
     * @param id The ID of the order to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<SuccessResponse<String>> deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        webSocketController.updateKitchen(orderService.findAllActiveOrders());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Order deleted successfully"));
    }
}
