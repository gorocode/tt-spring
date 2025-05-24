package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.InvoiceDTO;
import com.goro.tabletalk.entity.InvoiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between InvoiceDTO and InvoiceEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    /**
     * Converts an InvoiceDTO to an InvoiceEntity.
     * @param invoice The InvoiceDTO to convert
     * @return The converted InvoiceEntity
     */
    InvoiceEntity toInvoiceEntity(InvoiceDTO invoice);

    /**
     * Converts an InvoiceEntity to an InvoiceDTO.
     * @param invoice The InvoiceEntity to convert
     * @return The converted InvoiceDTO
     */
    InvoiceDTO toInvoiceDTO(InvoiceEntity invoice);

    /**
     * Converts a list of InvoiceEntity objects to a list of InvoiceDTO objects.
     * @param invoice The list of InvoiceEntity objects to convert
     * @return The list of converted InvoiceDTO objects
     */
    List<InvoiceDTO> toInvoiceDTO(List<InvoiceEntity> invoice);

}
