package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.InvoiceDTO;
import com.goro.tabletalk.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for managing invoice operations.
 * Provides endpoints for creating and retrieving invoices.
 */
@RestController
@RequestMapping("/invoice")
@Validated
public class InvoiceController {
    /** Service for handling invoice-related operations */
    @Autowired
    private InvoiceService invoiceService;

    /**
     * Creates a new invoice.
     * @param invoice The invoice data to save
     * @return ResponseEntity containing the saved invoice
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<InvoiceDTO> saveInvoice(@Valid @RequestBody InvoiceDTO invoice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.saveInvoice(invoice));
    }

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
     * @return ResponseEntity containing the list of filtered invoices
     */
    @GetMapping("/filters")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByFilters(
            @RequestParam(required = false) Long invoiceId,
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) Long tableNum,
            @RequestParam(required = false) String paymentType,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceService.findInvoicesByFilters(invoiceId, orderId, tableNum,
                        paymentType, min, max, startDate, endDate));
    }

}
