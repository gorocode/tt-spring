package com.goro.tabletalk.service;

import com.goro.tabletalk.dto.ProductDTO;
import com.goro.tabletalk.entity.MenuItemEntity;
import com.goro.tabletalk.entity.ProductEntity;
import com.goro.tabletalk.mapper.ProductMapper;
import com.goro.tabletalk.repository.ProductRepository;
import com.goro.tabletalk.repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.goro.tabletalk.repository.spec.ProductSpecs.hasName;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO product) {
        if (productRepository.findByNameIgnoreCase(product.getName()).isPresent()) {
            throw new NonUniqueResultException("Product with name " + product.getName() + " already exists");
        }
        return productMapper.toProductResponse(
                productRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) {
        productRepository.findById(product.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Product with id " + product.getId() + " not found"));
        Optional<ProductEntity> productEntity = productRepository.findByNameIgnoreCase(product.getName());

        if (productEntity.isPresent() && !Objects.equals(productEntity.get().getId(), product.getId())) {
            throw new NonUniqueResultException("Product with name " + product.getName() + " already exists");
        }
        return productMapper.toProductResponse(
                productRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productMapper.toProductResponse(
                productRepository.findByDeletedFalse());
    }

    @Override
    public List<ProductDTO> findProductByName(String name) {
        return productMapper.toProductResponse(productRepository.findAll(hasName(name)));
    }

    public ProductDTO findProductById(Long id) {
        return productMapper.toProductResponse(
                productRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Product with id " + id + " not found")));
    }

    @Override
    public void deleteProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));

        List<MenuItemEntity> menuItems = product.getMenuItems();

        if (menuItems != null && !menuItems.isEmpty()) {
            List<MenuItemEntity> remainingItems = new ArrayList<>();

            for (MenuItemEntity item : menuItems) {
                if (item.getOrderItems() == null || item.getOrderItems().isEmpty()) {
                    menuItemRepository.delete(item);
                } else {
                    item.setDeleted(true);
                    remainingItems.add(item);
                }
            }

            menuItemRepository.saveAll(remainingItems);

            product.setMenuItems(remainingItems);
            product.setDeleted(true);
            productRepository.save(product);
        } else {
            productRepository.deleteById(id);
        }
    }

}
