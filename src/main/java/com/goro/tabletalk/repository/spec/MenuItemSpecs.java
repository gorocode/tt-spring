package com.goro.tabletalk.repository.spec;

import com.goro.tabletalk.entity.MenuItemEntity;
import com.goro.tabletalk.entity.MenuItemEntity_;
import com.goro.tabletalk.entity.ProductEntity_;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification class for filtering {@link MenuItemEntity} instances.
 * Provides methods to create specifications for filtering menu items
 * based on menu, category, and product criteria.
 */
public class MenuItemSpecs {
    /**
     * Creates a specification to filter menu items by their menu ID.
     * 
     * @param menuId The ID of the menu to filter by
     * @return Specification filtering menu items by menu ID
     */
    public static Specification<MenuItemEntity> inMenu(Long menuId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("menu").get("id"), menuId);
        };
    }

    /**
     * Creates a specification to filter menu items by their category ID.
     * 
     * @param categoryId The ID of the category to filter by
     * @return Specification filtering menu items by category ID
     */
    public static Specification<MenuItemEntity> inCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    /**
     * Creates a specification to filter menu items by their product name.
     * Supports partial matches and multiple search terms (space-separated).
     * The search is case-insensitive.
     * 
     * @param term The search term(s) to match against product names
     * @return Specification filtering menu items by product name
     */
    public static Specification<MenuItemEntity> hasProduct(String term) {
        return (root, query, criteriaBuilder) -> {
            String[] terms = term.toLowerCase().split("\\s+");
            Predicate[] predicates = new Predicate[terms.length];
            var products = root.join(MenuItemEntity_.PRODUCT, JoinType.LEFT);
            for (int i = 0; i < terms.length; i++) {
                predicates[i] =
                        criteriaBuilder.like(
                                criteriaBuilder.lower(products.get(ProductEntity_.NAME)),
                                "%" + terms[i] + "%");
            }
            return criteriaBuilder.or(predicates);
        };
    }

}
