package com.softtech.softtechspringboot.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSaveAndUpdateResponseDto {

    private String name;
    private BigDecimal taxFreePrice;
    private Long categoryId;
    private BigDecimal lastPriceWithTax;
    private BigDecimal taxPrice;
}
