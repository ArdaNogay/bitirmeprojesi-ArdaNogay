package com.softtech.softtechspringboot.Dto;

import com.softtech.softtechspringboot.Enum.CategoryType;

import java.math.BigDecimal;

public interface ProductCategoryDetailResult {

    CategoryType getCategoryType();
    BigDecimal getTax();
    BigDecimal getMaxPrice();
    BigDecimal getMinPrice();
    BigDecimal getAvgPrice();
    BigDecimal getProductCount();
}
