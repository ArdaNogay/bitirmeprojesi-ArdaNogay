package com.softtech.softtechspringboot.Dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ProductSaveAndUpdateRequestDto {

    private String name;
    private BigDecimal taxFreePrice;
    private Long categoryId;
}
