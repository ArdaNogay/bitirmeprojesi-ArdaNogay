package com.softtech.softtechspringboot.Service;

import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.entity.Category;
import com.softtech.softtechspringboot.Enum.ErrorEnums.CategoryErrorMessage;
import com.softtech.softtechspringboot.Enum.ErrorEnums.GeneralErrorMessage;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import com.softtech.softtechspringboot.Service.EntityService.CategoryEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryEntityService categoryEntityService;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldSave() {

        CategorySaveAndUpdateRequestDto requestDto = mock(CategorySaveAndUpdateRequestDto.class);
        when(requestDto.getTax()).thenReturn(BigDecimal.valueOf(20));

        Category category = mock(Category.class);

        when(categoryEntityService.save(any())).thenReturn(category);

        CategorySaveAndUpdateRequestDto result = categoryService.save(requestDto);

        assertEquals(BigDecimal.valueOf(20), result.getTax());
    }

    @Test
    void shouldNotSaveWhenTaxIsUnderZero() {

        CategorySaveAndUpdateRequestDto requestDto = mock(CategorySaveAndUpdateRequestDto.class);
        when(requestDto.getTax()).thenReturn(BigDecimal.valueOf(-50));

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> categoryService.save(requestDto));

        assertEquals(CategoryErrorMessage.TAX_MUST_BE_ZERO_OR_GREATER, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenTaxIsNull() {

        CategorySaveAndUpdateRequestDto requestDto = mock(CategorySaveAndUpdateRequestDto.class);
        when(requestDto.getTax()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> categoryService.save(requestDto));
    }

    @Test
    void shouldNotSaveWhenParameterIsNull() {

        assertThrows(NullPointerException.class, () -> categoryService.save(null));
    }

//    @Test
//    void update() {
//        CategorySaveAndUpdateRequestDto requestDto = mock(CategorySaveAndUpdateRequestDto.class);
//        when(requestDto.getTax()).thenReturn(BigDecimal.ZERO);
//
//        Category category = mock(Category.class);
//        category.setTax(BigDecimal.TEN);
//        when(category.getId()).thenReturn(1L);
//
//        List<Product> productList = new ArrayList<>();
//        Product product = mock(Product.class);
//        productList.add(product);
//        when(product.getTaxFreePrice()).thenReturn(BigDecimal.TEN);
//
//        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);
//        when(productEntityService.findAll()).thenReturn(productList);
//        when(productEntityService.save(any())).thenReturn(product);
//
//        when(categoryEntityService.findByCategoryType(any())).thenReturn(category);
//        when(categoryEntityService.save(category)).thenReturn(category);
//        doNothing().when(productService).priceRegulator(anyLong());
//        CategorySaveAndUpdateRequestDto result = categoryService.update(requestDto);
//        assertEquals(BigDecimal.ZERO, result.getTax());
//    }

    @Test
    void shouldDelete() {
        categoryService.delete(anyLong());
        verify(categoryEntityService).deleteByIdWithControl(any());
    }

    @Test
    void shouldNotDeleteWhenCategoryIdIsInvalid() {
        doThrow(new InvalidParameterExceptions(GeneralErrorMessage.INVALID_REQUEST)).when(categoryEntityService).deleteByIdWithControl(anyLong());
        InvalidParameterExceptions result = assertThrows(InvalidParameterExceptions.class, () -> categoryService.delete(anyLong()));
        verify(categoryEntityService).deleteByIdWithControl(any());
        assertNotNull(result);
    }

}