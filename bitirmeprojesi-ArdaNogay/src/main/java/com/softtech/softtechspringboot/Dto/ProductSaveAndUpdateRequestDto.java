package com.softtech.softtechspringboot.Dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductSaveAndUpdateRequestDto {

    private String name;
    private BigDecimal taxFreePrice;
    private Long categoryId;
}
