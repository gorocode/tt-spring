package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.OrderDTO;
import com.goro.tabletalk.dto.OrderItemDTO;
import com.goro.tabletalk.entity.OrderEntity;
import com.goro.tabletalk.entity.OrderItemEntity;
import com.goro.tabletalk.enumeration.OrderStatusEnum;
import com.goro.tabletalk.mapper.OrderItemMapper;
import com.goro.tabletalk.mapper.OrderMapper;
import com.goro.tabletalk.repository.OrderRepository;
import com.goro.tabletalk.repository.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.goro.tabletalk.repository.spec.OrderSpecs.*;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderDTO saveOrder(Long tableId) {
        List<OrderEntity> orderFound = orderRepository.findByTableIdAndStatus(tableId, OrderStatusEnum.PENDING);
        if (!orderFound.isEmpty()) {
            throw new NonUniqueResultException("Table with id " + tableId + " already has an order.");
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTable(tableRepository.findById(tableId).orElseThrow(
                () -> new EntityNotFoundException("Table with id " + tableId + " not found.")));
        orderEntity.setStatus(OrderStatusEnum.PENDING);
        orderEntity.setDate(LocalDateTime.now());
        orderEntity.setTotalWithoutTax(BigDecimal.valueOf(0.0));
        orderEntity.setTotalWithTax(BigDecimal.valueOf(0.0));
        return orderMapper.toOrderResponse(orderRepository.save(orderEntity));
    }

    @Override
    public OrderDTO saveFullOrder(OrderDTO order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(LocalDateTime.now());
        orderEntity.setTable(tableRepository.findById(order.getTable().getId()).orElseThrow(
                () -> new EntityNotFoundException("Table with id " + order.getTable().getId() + " not found.")));
        orderEntity.setStatus(order.getStatus());

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (OrderItemDTO orderItem : order.getOrderItems()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemMapper.updateEntityFromDto(orderItem, orderItemEntity);
            orderItemEntity.setOrder(orderEntity);
            orderItemEntities.add(orderItemEntity);
        }
        orderEntity.setOrderItems(orderItemEntities);

        return orderMapper.toOrderResponse(orderRepository.save(updateOrderTotals(orderEntity)));
    }

    @Override
    @Transactional
    public List<OrderDTO> findAllActiveOrders() {
        return orderMapper.toOrderResponse(orderRepository.findByStatus(OrderStatusEnum.IN_PROGRESS));
    }

    @Override
    @Transactional
    public OrderDTO findActiveOrderByTableId(Long id) {

        List<OrderEntity> orderEntities = orderRepository.findByTableIdAndStatus(id, OrderStatusEnum.PENDING);
        OrderEntity orderEntity;
        if (orderEntities.isEmpty()) {
            orderEntity = new OrderEntity();
            orderEntity.setTable(tableRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Table with id " + id + " not found")));
        } else {
            orderEntity = orderEntities.getFirst();
        }
        return orderMapper.toOrderResponse(orderRepository.save(orderEntity));
    }

    @Override
    public List<OrderDTO> getOrdersByFilters(Long orderId, Long tableId, List<OrderStatusEnum> status, Boolean paid,
                                             LocalDateTime startDate, LocalDateTime endDate) {
        Specification<OrderEntity> spec = Specification.where(null);

        if (orderId != null) {
            spec = spec.and(hasOrderId(orderId));
        }
        if (tableId != null) {
            spec = spec.and(hasTableId(tableId));
        }
        if (status != null && !status.isEmpty()) {
            spec = spec.and(hasStatus(status));
        }
        if (paid != null) {
            spec = spec.and(isPaid(paid));
        }
        if (startDate != null) {
            spec = spec.and(afterDate(startDate));
        }
        if (endDate != null) {
            spec = spec.and(beforeDate(endDate));
        }

        return orderMapper.toOrderResponse(orderRepository.findAll(spec));
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO order) {
        OrderEntity orderEntity = updateOrderTotals(orderMapper.toOrderEntity(order));
        OrderEntity orderEntity1 = orderRepository.findById(order.getId()).orElseThrow(() -> new EntityNotFoundException("Order with id " + order.getId() + " not found"));

        if (orderEntity1.getInvoice() != null) {
            orderEntity.setInvoice(orderEntity1.getInvoice());
        }
        for (OrderItemEntity orderItem : orderEntity.getOrderItems()) {
            orderItem.setOrder(orderEntity);
        }

        return orderMapper.toOrderResponse(orderRepository.save(orderEntity));
    }

    @Override
    public OrderDTO findById(Long id) {
        return orderMapper.toOrderResponse(orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found")));
    }

    public OrderEntity updateOrderTotals(OrderEntity order) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalWithoutTax = BigDecimal.ZERO;
        for (OrderItemEntity item : order.getOrderItems()) {
            if (item.getPrice() != null && item.getTax() != null) {
                item.setPrice(item.getMenuItem().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                total = total.add(item.getPrice());
                BigDecimal priceWithoutTax = item.getPrice().divide(BigDecimal.ONE.add(item.getTax()), 6,
                        RoundingMode.HALF_UP);
                totalWithoutTax = totalWithoutTax.add(priceWithoutTax);
            }
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        totalWithoutTax = totalWithoutTax.setScale(2, RoundingMode.HALF_UP);
        order.setTotalWithTax(total);
        order.setTotalWithoutTax(totalWithoutTax);
        return order;
    }
}
