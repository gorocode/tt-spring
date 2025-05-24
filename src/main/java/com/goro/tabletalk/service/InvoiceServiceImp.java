package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.InvoiceDTO;
import com.goro.tabletalk.entity.InvoiceEntity;
import com.goro.tabletalk.entity.OrderEntity;
import com.goro.tabletalk.enumeration.OrderStatusEnum;
import com.goro.tabletalk.mapper.InvoiceMapper;
import com.goro.tabletalk.mapper.OrderMapper;
import com.goro.tabletalk.repository.InvoiceRepository;
import com.goro.tabletalk.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.goro.tabletalk.repository.spec.InvoiceSpecs.*;

@Service
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public InvoiceDTO saveInvoice(InvoiceDTO invoice) {
        InvoiceEntity invoiceEntity = invoiceMapper.toInvoiceEntity(invoice);
        OrderEntity orderEntity = orderRepository.findById(invoiceEntity.getOrder().getId()).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + invoiceEntity.getOrder().getId() + " not found"));
        invoice.setDate(LocalDateTime.now());
        invoiceEntity.setOrder(orderEntity);
        InvoiceEntity savedInvoice = invoiceRepository.save(invoiceEntity);

        if (orderEntity.getStatus() == OrderStatusEnum.COMPLETED) {
            orderEntity.setStatus(OrderStatusEnum.FINISHED);
        }
        orderEntity.setPaid(true);
        orderEntity.setInvoice(invoiceEntity);
        orderRepository.save(orderEntity);

        return invoiceMapper.toInvoiceDTO(savedInvoice);
    }

    @Override
    public InvoiceDTO findById(Long id) {
        return invoiceMapper.toInvoiceDTO(invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Invoice with id " + id + " not found")));
    }

    @Override
    public List<InvoiceDTO> findInvoicesByFilters(Long invoiceId, Long orderId, Long tableNum, String paymentType, BigDecimal min, BigDecimal max, LocalDateTime startDate, LocalDateTime endDate) {
        Specification<InvoiceEntity> spec = Specification.where(null);

        if (invoiceId != null) {
            spec = spec.and(hasInvoiceId(invoiceId));
        }

        if (orderId != null) {
            spec = spec.and(hasOrderId(orderId));
        }

        if (tableNum != null) {
            spec = spec.and(hasTableId(tableNum));
        }

        if (min != null) {
            spec = spec.and(totalMin(min));
        }

        if (max != null) {
            spec = spec.and(totalMax(max));
        }

        if (startDate != null) {
            spec = spec.and(afterDate(startDate));
        }

        if (endDate != null) {
            spec = spec.and(beforeDate(endDate));
        }

        switch (paymentType) {
            case "all":
                break;
            case "card":
                spec = spec.and(onlyCard());
                break;
            case "cash":
                spec = spec.and(onlyCash());
                break;
            case "mixed":
                spec = spec.and(mixedPayment());
                break;
        }
        return invoiceMapper.toInvoiceDTO(invoiceRepository.findAll(spec));
    }
}
