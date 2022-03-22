package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.Dto.ProductCategoryDetailResult;
import com.softtech.softtechspringboot.Entity.Product;
import com.softtech.softtechspringboot.Repository.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductEntityService  extends BaseEntityService<Product ,ProductDao>{
    public ProductEntityService(ProductDao productDao) {
        super(productDao);
    }

    public List<Product> findProductsByCategoryId(Long id){
        return getDao().findProductsByCategoryId(id);
    }

    public List<Product> findProductsByLastPriceWithTaxBetween(BigDecimal smallPrice , BigDecimal bigPrice){
        return getDao().findProductsByLastPriceWithTaxBetween(smallPrice,bigPrice);
    }

    public List<ProductCategoryDetailResult> getProductCategoryDetails(){
        return getDao().getProductCategoryDetails();
    }

}
