package com.goro.tabletalk.controller;

import com.goro.tabletalk.dto.ApiDTO.SuccessResponse;
import com.goro.tabletalk.dto.ProductDTO;
import com.goro.tabletalk.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 * Provides endpoints for creating, updating, retrieving, and deleting products.
 */
@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    /** Service for handling product-related operations */
    @Autowired
    private ProductService productService;

    /**
     * Creates a new product.
     * @param product The product data to create
     * @return ResponseEntity containing the created product
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProduct(product));
    }

    /**
     * Updates an existing product.
     * @param product The product data to update
     * @return ResponseEntity containing the updated product
     */
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductDTO> putProductById(@Valid @RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(product));
    }

    /**
     * Retrieves all products.
     * @return ResponseEntity containing the list of all products
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WORKER', 'CUSTOMER')")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllProducts());
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<SuccessResponse<String>> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", "Product deleted successfully"));
    }
}
