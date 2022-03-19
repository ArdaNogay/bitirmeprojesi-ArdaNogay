package com.softtech.softtechspringboot.Dto;

import com.softtech.softtechspringboot.Enum.CategoryType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategorySaveAndUpdateRequestDto {

    private CategoryType categoryType;
    private BigDecimal tax; //Todo: BigDecimal yapılacak burası
}
