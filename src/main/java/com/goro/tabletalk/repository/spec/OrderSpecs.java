package com.goro.tabletalk.repository.spec;

import com.goro.tabletalk.entity.OrderEntity;
import com.goro.tabletalk.enumeration.OrderStatusEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Specification class for filtering {@link OrderEntity} instances.
 * Provides methods to create specifications for filtering orders
 * based on various criteria such as ID, table, date, status, and payment.
 */
public class OrderSpecs {
    /**
     * Creates a specification to filter orders by their ID.
     * 
     * @param orderId The ID of the order to find
     * @return Specification filtering by order ID, or all if ID is null
     */
    public static Specification<OrderEntity> hasOrderId(Long orderId) {
        return (root, query, criteriaBuilder) -> {
            if (orderId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), orderId);
        };
    }

    /**
     * Creates a specification to filter orders by their table ID.
     * 
     * @param tableId The ID of the table to find orders for
     * @return Specification filtering by table ID, or all if ID is null
     */
    public static Specification<OrderEntity> hasTableId(Long tableId) {
        return (root, query, criteriaBuilder) -> {
            if (tableId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("table").get("id"), tableId);
        };
    }

    /**
     * Creates a specification to filter orders before a specific date.
     * 
     * @param date The date to compare against
     * @return Specification filtering by date, or all if date is null
     */
    public static Specification<OrderEntity> beforeDate(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("date"), date);
        };
    }

    /**
     * Creates a specification to filter orders after a specific date.
     * 
     * @param date The date to compare against
     * @return Specification filtering by date, or all if date is null
     */
    public static Specification<OrderEntity> afterDate(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("date"), date);
        };
    }

    /**
     * Creates a specification to filter orders by their status.
     * 
     * @param statuses List of order statuses to filter by
     * @return Specification filtering by status, or all if statuses is null or empty
     */
    public static Specification<OrderEntity> hasStatus(List<OrderStatusEnum> statuses) {
        return (root, query, criteriaBuilder) -> {
            if (statuses == null || statuses.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("status").in(statuses);
        };
    }

    /**
     * Creates a specification to filter orders by their payment status.
     * 
     * @param paid True to find paid orders, false for unpaid orders
     * @return Specification filtering by payment status, or all if paid is null
     */
    public static Specification<OrderEntity> isPaid(Boolean paid) {
        return (root, query, criteriaBuilder) -> {
            if (paid == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("paid"), paid);
        };
    }
}
