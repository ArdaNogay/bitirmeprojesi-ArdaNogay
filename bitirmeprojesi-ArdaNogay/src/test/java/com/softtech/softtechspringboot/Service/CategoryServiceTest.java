package com.softtech.softtechspringboot.Service;

import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.Category;
import com.softtech.softtechspringboot.Enum.ErrorEnums.CategoryErrorMessage;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import com.softtech.softtechspringboot.Service.EntityService.CategoryEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryEntityService categoryEntityService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CategoryService categoryService;

//    @Test
//    void setProductService() {
//    }

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

    @Test
    void update() {
    }

//    @Test
//    void shouldDelete() {
//        CategoryDeleteDto categoryDeleteDto = mock(CategoryDeleteDto.class);
//        when(categoryDeleteDto.getCategoryType()).thenReturn(CategoryType.OTHER);
////        Category category = mock(Category.class);
////
////        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);
//
//        categoryService.delete(categoryDeleteDto);
//
////        verify(categoryEntityService).getByIdWithControl(anyLong());
////        verify(categoryEntityService).delete(any());
//    }

//    @Test
//    void shouldNotDeleteWhenCategoryTypeIsNull() {
//        CategoryDeleteDto categoryDeleteDto = mock(CategoryDeleteDto.class);
//        when(categoryDeleteDto.getCategoryType()).thenReturn(null);
//
//        assertThrows(EntityNotFoundExceptions.class, () -> categoryService.delete(categoryDeleteDto));
//
//    }

}