package com.softtech.softtechspringboot.Dto;

import com.softtech.softtechspringboot.Enum.CategoryType;
import lombok.Data;

@Data
public class CategorySaveAndUpdateRequestDto {

    private CategoryType categoryType;
    private Double tax; //Todo: BigDecimal yapılacak burası
}
