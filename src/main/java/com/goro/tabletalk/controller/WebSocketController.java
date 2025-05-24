package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.InvoiceDTO;
import com.goro.tabletalk.dto.OrderDTO;
import com.goro.tabletalk.enumeration.OrderStatusEnum;
import com.goro.tabletalk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket controller for handling real-time communication for orders, kitchen, and printer operations.
 * This controller manages table orders, kitchen updates, and printer ticket generation.
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private OrderService orderService;

    /**
     * Handles table subscription requests and sends current order status.
     * @param payload Map containing tableId
     */
    @MessageMapping("/order/subscribe")
    public void handleTableSubscription(Map<String, Long> payload) {
        Long tableId = payload.get("tableId");
        OrderDTO currentOrder = orderService.findActiveOrderByTableId(tableId);
        messagingTemplate.convertAndSend("/topic/table/" + tableId, currentOrder);
    }

    /**
     * Processes table orders and updates their status.
     * @param order The order to be processed
     * @return Updated OrderDTO
     */
    @MessageMapping("/order")
    public OrderDTO processTableOrder(OrderDTO order) {
        OrderDTO orderDTO = orderService.updateOrder(order);

        if (orderDTO.getStatus() == OrderStatusEnum.IN_PROGRESS) {
            return updateTableOrder(orderService.saveOrder(orderDTO.getTable().getId()));
        } else {
            return updateTableOrder(orderDTO);
        }
    }

    /**
     * Updates table order and notifies relevant subscribers.
     * @param order The order to be updated
     * @return Updated OrderDTO
     */
    public OrderDTO updateTableOrder(OrderDTO order) {
        String topic = "/topic/table/" + order.getTable().getId();
        messagingTemplate.convertAndSend(topic, order);
        updateKitchen(orderService.findAllActiveOrders());
        return order;
    }

    /**
     * Handles kitchen subscription requests and sends all active orders.
     * @return List of active orders
     */
    @MessageMapping("/kitchen/subscribe")
    public List<OrderDTO> handleKitchenSubscription() {
        List<OrderDTO> orderList = orderService.findAllActiveOrders();
        updateKitchen(orderList);
        return orderList;
    }

    /**
     * Processes kitchen orders and updates all subscribers.
     * @param order The order to be processed
     * @return Updated list of active orders
     */
    @MessageMapping("/kitchen")
    public List<OrderDTO> processKitchenOrders(OrderDTO order) {
        orderService.updateOrder(order);
        List<OrderDTO> orderList = orderService.findAllActiveOrders();
        updateKitchen(orderList);
        return orderList;
    }

    /**
     * Updates kitchen subscribers with current order list.
     * @param orderList List of orders to broadcast
     */
    public void updateKitchen(List<OrderDTO> orderList) {
        messagingTemplate.convertAndSend("/topic/kitchen/orders", orderList);
    }

    /**
     * Handles printer subscription requests.
     * @param payload Map containing printer identifier
     * @return Connection status message
     */
    @MessageMapping("/printer/subscribe")
    public String handlePrinterSubscription(Map<String, String> payload) {
        String printer = payload.get("printer");
        messagingTemplate.convertAndSend("/topic/printer/" + printer, "Successfully connected");
        return "Successfully connected";
    }

    /**
     * Sends order ticket to specified printer.
     * @param order Order to be printed
     * @param printer Target printer identifier
     */
    public void printOrderTicket(OrderDTO order, String printer) {
        String topic = "/topic/printer/" + printer;

        Map<String, Object> message = new HashMap<>();
        message.put("type", "order");
        message.put("content", order);

        messagingTemplate.convertAndSend(topic, message);
    }

    /**
     * Sends invoice ticket to specified printer.
     * @param invoice Invoice to be printed
     * @param printer Target printer identifier
     */
    public void printInvoiceTicket(InvoiceDTO invoice, String printer) {
        String topic = "/topic/printer/" + printer;

        Map<String, Object> message = new HashMap<>();
        message.put("type", "invoice");
        message.put("content", invoice);

        messagingTemplate.convertAndSend(topic, message);
    }
}
