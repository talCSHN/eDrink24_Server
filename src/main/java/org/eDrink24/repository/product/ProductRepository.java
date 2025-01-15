package org.eDrink24.repository.product;

import org.eDrink24.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE p.productName LIKE :keyword " +
            "OR p.category1 LIKE :keyword " +
            "OR p.category2 LIKE :keyword")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
}
