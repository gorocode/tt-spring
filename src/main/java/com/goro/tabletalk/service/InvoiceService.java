package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.InvoiceDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface for managing invoice operations.
 * Provides methods for creating, retrieving, and filtering invoices.
 */
public interface InvoiceService {
    /**
     * Creates a new invoice.
     * @param invoice The invoice data to save
     * @return The saved invoice
     */
    InvoiceDTO saveInvoice(InvoiceDTO invoice);

    /**
     * Retrieves an invoice by its ID.
     * @param id The ID of the invoice to find
     * @return The found invoice
     */
    InvoiceDTO findById(Long id);

    /**
     * Retrieves invoices based on various filter criteria.
     * @param invoiceId Optional invoice ID filter
     * @param orderId Optional order ID filter
     * @param tableNum Optional table number filter
     * @param paymentType Optional payment type filter
     * @param min Optional minimum amount filter
     * @param max Optional maximum amount filter
     * @param startDate Optional start date filter
     * @param endDate Optional end date filter
     * @return List of invoices matching the filter criteria
     */
    List<InvoiceDTO> findInvoicesByFilters(Long invoiceId, Long orderId, Long tableNum, 
            String paymentType, BigDecimal min, BigDecimal max, 
            LocalDateTime startDate, LocalDateTime endDate);
}
