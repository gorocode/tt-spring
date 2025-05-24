package com.goro.tabletalk.repository.spec;

import com.goro.tabletalk.entity.ProductEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification class for filtering {@link ProductEntity} instances.
 * Provides methods to create specifications for filtering products
 * based on name criteria.
 */
public class ProductSpecs {
    /**
     * Creates a specification to filter products by their name.
     * Supports partial matches and multiple search terms (space-separated).
     * The search is case-insensitive.
     * 
     * @param term The search term(s) to match against product names
     * @return Specification filtering products by name
     */
    public static Specification<ProductEntity> hasName(String term) {
        return (root, query, criteriaBuilder) -> {
            String[] terms = term.toLowerCase().split("\\s+");
            Predicate[] predicates = new Predicate[terms.length];
            for (int i = 0; i < terms.length; i++) {
                predicates[i] =
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + terms[i] + "%");
            }
            return criteriaBuilder.or(predicates);
        };
    }
}
