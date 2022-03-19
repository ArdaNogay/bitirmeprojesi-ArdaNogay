package com.softtech.softtechspringboot.Repository;

import com.softtech.softtechspringboot.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product ,Long> {

    List<Product> findProductsByCategoryId(Long id);

    List<Product> findProductsByLastPriceWithTaxBetween(BigDecimal smallPrice ,BigDecimal bigPrice);
}
