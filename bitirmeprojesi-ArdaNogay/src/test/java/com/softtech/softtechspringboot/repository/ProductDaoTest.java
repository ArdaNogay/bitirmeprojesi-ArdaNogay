package com.softtech.softtechspringboot.repository;

import com.softtech.softtechspringboot.Dto.ProductCategoryDetailResult;
import com.softtech.softtechspringboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    void findProductsByCategoryId() {
        List<Product> result = productDao.findProductsByCategoryId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void findProductsByLastPriceWithTaxBetween() {
        List<Product> result = productDao.findProductsByLastPriceWithTaxBetween(BigDecimal.valueOf(1), BigDecimal.valueOf(1000));
        assertNotNull(result);
        assertEquals(6, result.size());
    }

    @Test
    void getProductCategoryDetails() {
        List<ProductCategoryDetailResult> result = productDao.getProductCategoryDetails();
        assertNotNull(result);
        assertEquals(5, result.size());
    }
}
