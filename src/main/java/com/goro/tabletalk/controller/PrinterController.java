package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.InvoiceDTO;
import com.goro.tabletalk.dto.OrderDTO;
import com.goro.tabletalk.service.InvoiceService;
import com.goro.tabletalk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing printer operations.
 * Handles printing of orders and invoices to specified printers
 * using WebSocket for real-time communication.
 */
@RestController
@RequestMapping("/print")
public class PrinterController {
    /** Service for managing orders */
    @Autowired
    private OrderService orderService;

    /** Service for managing invoices */
    @Autowired
    private InvoiceService invoiceService;

    /** Controller for sending real-time print commands via WebSocket */
    @Autowired
    private WebSocketController webSocketController;

    /**
     * Prints an order ticket to a specified printer.
     * @param orderId ID of the order to print
     * @param printer Name or identifier of the target printer
     * @return The order that was printed
     */
    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public OrderDTO printOrder(@PathVariable("orderId") Long orderId, @RequestParam String printer) {
        OrderDTO orderDTO = orderService.findById(orderId);
        webSocketController.printOrderTicket(orderDTO, printer);
        return orderDTO;
    }

    /**
     * Prints an invoice to a specified printer.
     * @param invoiceId ID of the invoice to print
     * @param printer Name or identifier of the target printer
     */
    @GetMapping("/invoice/{invoiceId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER')")
    public InvoiceDTO printInvoice(@PathVariable("invoiceId") Long invoiceId, @RequestParam String printer) {
        InvoiceDTO invoiceDTO = invoiceService.findById(invoiceId);
        webSocketController.printInvoiceTicket(invoiceDTO, printer);
        return invoiceDTO;
    }
}
