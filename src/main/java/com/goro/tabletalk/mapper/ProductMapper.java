package com.goro.tabletalk.mapper;

import com.goro.tabletalk.dto.ProductDTO;
import com.goro.tabletalk.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between Product DTOs and Entities.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    /**
     * Converts a ProductDTO to a ProductEntity.
     * @param product The product DTO to convert
     * @return The converted product entity
     */
    ProductEntity toProductEntity(ProductDTO product);

    /**
     * Converts a ProductEntity to a ProductDTO.
     * @param product The product entity to convert
     * @return The converted product DTO
     */
    ProductDTO toProductResponse(ProductEntity product);

    /**
     * Converts a list of ProductEntity objects to DTOs.
     * @param product The list of product entities to convert
     * @return The list of converted product DTOs
     */
    List<ProductDTO> toProductResponse(List<ProductEntity> product);

}
