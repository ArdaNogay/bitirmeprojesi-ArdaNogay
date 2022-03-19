package com.softtech.softtechspringboot.Dto;

import lombok.Data;

@Data
public class ProductSaveAndUpdateResponseDto {

    private String name;
    private Double taxFreePrice;
    private Long categoryId;
    private Double lastPriceWithTax;
    private Double taxPrice;
}
