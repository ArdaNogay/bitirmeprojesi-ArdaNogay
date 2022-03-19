package com.softtech.softtechspringboot.Dto;

import lombok.Data;


@Data
public class ProductSaveAndUpdateRequestDto {

    private String name;
    private Double taxFreePrice;
    private Long categoryId;
}
