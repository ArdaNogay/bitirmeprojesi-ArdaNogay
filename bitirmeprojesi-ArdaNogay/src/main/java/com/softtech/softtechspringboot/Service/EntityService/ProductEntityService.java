package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.Entity.Product;
import com.softtech.softtechspringboot.Repository.ProductDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductEntityService  extends BaseEntityService<Product ,ProductDao>{
    public ProductEntityService(ProductDao productDao) {
        super(productDao);
    }

    public Product getById(Long id){
        return getDao().getById(id);
    }

    public List<Product> findProductsByCategoryId(Long id){
        return getDao().findProductsByCategoryId(id);
    }

    public List<Product> findProductsByLastPriceWithTaxBetween(BigDecimal smallPrice , BigDecimal bigPrice){
        return getDao().findProductsByLastPriceWithTaxBetween(smallPrice,bigPrice);
    }

}
