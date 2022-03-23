package com.softtech.softtechspringboot.Dto;

import com.softtech.softtechspringboot.Enum.CategoryType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategorySaveAndUpdateRequestDto {

    private CategoryType categoryType;
    private BigDecimal tax;
}
