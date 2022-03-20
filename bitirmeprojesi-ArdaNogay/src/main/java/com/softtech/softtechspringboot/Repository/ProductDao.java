package com.softtech.softtechspringboot.Repository;

import com.softtech.softtechspringboot.Dto.ProductCategoryDetailResult;
import com.softtech.softtechspringboot.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategoryId(Long id);

    List<Product> findProductsByLastPriceWithTaxBetween(BigDecimal smallPrice, BigDecimal bigPrice);

    @Query(value = "SELECT " +
            "category.category_type as categoryType, " +
            "category.tax as tax, " +
            "max(product.last_price_with_tax) as maxPrice, " +
            "min(product.last_price_with_tax) as minPrice, " +
            "avg(product.last_price_with_tax) as avgPrice, " +
            "count(product.name) as productCount " +
            "FROM Product product " +
            "LEFT JOIN Category category " +
            "ON product.id_category = category.id " +
            "GROUP BY category.id ", nativeQuery = true)
    List<ProductCategoryDetailResult> getProductCategoryDetails();

}
