package com.softtech.softtechspringboot.Dto;

import com.softtech.softtechspringboot.Enum.CategoryType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCategoryDetailResponseDto {

    private CategoryType categoryType;
    private BigDecimal tax;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal avgPrice;
    private BigDecimal productCount;
}
