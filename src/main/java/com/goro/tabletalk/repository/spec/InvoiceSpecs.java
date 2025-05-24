package com.goro.tabletalk.repository.spec;

import com.goro.tabletalk.entity.InvoiceEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Specification class for filtering {@link InvoiceEntity} instances.
 * Provides various methods to create specifications for filtering invoices
 * based on different criteria.
 */
public class InvoiceSpecs {
    /**
     * Creates a specification to filter invoices by their ID.
     * 
     * @param invoiceId The ID of the invoice to find
     * @return Specification filtering by invoice ID, or all if ID is null
     */
    public static Specification<InvoiceEntity> hasInvoiceId(Long invoiceId) {
        return (root, query, criteriaBuilder) -> {
            if (invoiceId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), invoiceId);
        };
    }


    /**
     * Creates a specification to filter invoices by their associated order ID.
     * 
     * @param orderId The ID of the order to find invoices for
     * @return Specification filtering by order ID, or all if ID is null
     */
    public static Specification<InvoiceEntity> hasOrderId(Long orderId) {
        return (root, query, criteriaBuilder) -> {
            if (orderId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("order").get("id"), orderId);
        };
    }

    /**
     * Creates a specification to filter invoices by their associated table ID.
     * 
     * @param tableId The ID of the table to find invoices for
     * @return Specification filtering by table ID, or all if ID is null
     */
    public static Specification<InvoiceEntity> hasTableId(Long tableId) {
        return (root, query, criteriaBuilder) -> {
            if (tableId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("order").get("table").get("id"), tableId);
        };
    }

    /**
     * Creates a specification to filter invoices before a specific date.
     * 
     * @param date The date to compare against
     * @return Specification filtering by date, or all if date is null
     */
    public static Specification<InvoiceEntity> beforeDate(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("date"), date);
        };
    }

    /**
     * Creates a specification to filter invoices after a specific date.
     * 
     * @param date The date to compare against
     * @return Specification filtering by date, or all if date is null
     */
    public static Specification<InvoiceEntity> afterDate(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("date"), date);
        };
    }

    /**
     * Creates a specification to filter invoices paid only with card.
     * 
     * @return Specification filtering invoices with card payment only
     */
    public static Specification<InvoiceEntity> onlyCard() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThan(root.get("paidWithCard"), 0),
                criteriaBuilder.equal(root.get("paidWithCash"), 0)
        );
    }

    /**
     * Creates a specification to filter invoices paid only with cash.
     * 
     * @return Specification filtering invoices with cash payment only
     */
    public static Specification<InvoiceEntity> onlyCash() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThan(root.get("paidWithCash"), 0),
                criteriaBuilder.equal(root.get("paidWithCard"), 0)
        );
    }

    /**
     * Creates a specification to filter invoices paid with both cash and card.
     * 
     * @return Specification filtering invoices with mixed payment methods
     */
    public static Specification<InvoiceEntity> mixedPayment() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThan(root.get("paidWithCash"), 0),
                criteriaBuilder.greaterThan(root.get("paidWithCard"), 0)
        );
    }

    /**
     * Creates a specification to filter invoices with a minimum total amount.
     * 
     * @param min The minimum total amount (inclusive)
     * @return Specification filtering by minimum total
     */
    public static Specification<InvoiceEntity> totalMin(BigDecimal min) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("order").get("totalWithTax"), min);
    }

    /**
     * Creates a specification to filter invoices with a maximum total amount.
     * 
     * @param max The maximum total amount (inclusive)
     * @return Specification filtering by maximum total
     */
    public static Specification<InvoiceEntity> totalMax(BigDecimal max) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("order").get("totalWithTax"), max);
    }
}
