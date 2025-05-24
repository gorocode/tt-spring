package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.ProductDTO;

import java.util.List;

/**
 * Service interface for managing product operations.
 */
public interface ProductService {
    /**
     * Saves a new product.
     * @param product The product to save
     * @return The saved product
     */
    ProductDTO saveProduct(ProductDTO product);

    /**
     * Updates an existing product.
     * @param product The product to update
     * @return The updated product
     */
    ProductDTO updateProduct(ProductDTO product);

    /**
     * Retrieves all products.
     * @return List of all products
     */
    List<ProductDTO> findAllProducts();

    /**
     * Finds products by name.
     * @param name The name to search for
     * @return List of products matching the name
     */
    List<ProductDTO> findProductByName(String name);

    /**
     * Finds a product by its ID.
     * @param id The ID of the product
     * @return The found product
     */
    ProductDTO findProductById(Long id);

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete
     */
    void deleteProductById(Long id);

}
