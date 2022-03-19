package com.softtech.softtechspringboot.Service;

import com.softtech.softtechspringboot.Converter.CategoryMapper;
import com.softtech.softtechspringboot.Dto.CategoryDeleteDto;
import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.Category;
import com.softtech.softtechspringboot.Enum.CategoryType;
import com.softtech.softtechspringboot.Enum.ErrorEnums.CategoryErrorMessage;
import com.softtech.softtechspringboot.Enum.ErrorEnums.GeneralErrorMessage;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import com.softtech.softtechspringboot.Service.EntityService.CategoryEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryEntityService categoryEntityService;
    private ProductService productService;

    @Autowired  /** Circular dependency*/
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public CategorySaveAndUpdateRequestDto save(CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto) {
        taxValidation(categorySaveAndUpdateRequestDto);
        Category category = CategoryMapper.INSTANCE.convertToCategory(categorySaveAndUpdateRequestDto);
        categoryEntityService.save(category);
        CategorySaveAndUpdateRequestDto requestDto = CategoryMapper.INSTANCE.convertToCategorySaveAndUpdateRequestDto(category);
        return requestDto;
    }

    public CategorySaveAndUpdateRequestDto update(CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto) {
        taxValidation(categorySaveAndUpdateRequestDto);
        Category category = categoryExistValidation(categorySaveAndUpdateRequestDto.getCategoryType());
        category.setTax(categorySaveAndUpdateRequestDto.getTax());
        categoryEntityService.save(category);
        productService.priceRegulator(category.getId());
        CategorySaveAndUpdateRequestDto requestDto = CategoryMapper.INSTANCE.convertToCategorySaveAndUpdateRequestDto(category);
        return requestDto;
    }

    public void delete(CategoryDeleteDto categoryDeleteDto) {
        Category category = categoryExistValidation(categoryDeleteDto.getCategoryType());
        categoryEntityService.delete(category);
    }

    private Category categoryExistValidation(CategoryType categoryType) {
        Category category = categoryEntityService.findByCategoryType(categoryType);
        if (category == null) {
            throw new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND);
        }
        return category;
    }

    private void taxValidation(CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto) {
        if (categorySaveAndUpdateRequestDto.getTax().compareTo(BigDecimal.ZERO)==-1) {
            throw new InvalidParameterExceptions(CategoryErrorMessage.TAX_MUST_BE_ZERO_OR_GREATER);
        }
    }
}
