package com.softtech.softtechspringboot.Converter;

import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategorySaveAndUpdateRequestDto convertToCategorySaveAndUpdateRequestDto(Category category);

    Category convertToCategory(CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto);
}
