package com.softtech.softtechspringboot.Service;

import com.softtech.softtechspringboot.Dto.*;
import com.softtech.softtechspringboot.entity.Category;
import com.softtech.softtechspringboot.entity.Product;
import com.softtech.softtechspringboot.Enum.ErrorEnums.GeneralErrorMessage;
import com.softtech.softtechspringboot.Enum.ErrorEnums.ProductErrorMessage;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import com.softtech.softtechspringboot.Service.EntityService.CategoryEntityService;
import com.softtech.softtechspringboot.Service.EntityService.ProductEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductEntityService productEntityService;

    @Mock
    private CategoryEntityService categoryEntityService;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldSave() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.TEN);
        when(requestDto.getCategoryId()).thenReturn(1L);


        Product product = mock(Product.class);

        Category category = mock(Category.class);
        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);
        when(category.getTax()).thenReturn(BigDecimal.ONE);

        when(productEntityService.save(any())).thenReturn(product);

        ProductSaveAndUpdateResponseDto result = productService.save(requestDto);

        assertEquals(1, result.getCategoryId());

    }

    @Test
    void shouldNotSaveWhenNameOfDtoIsNull() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn(null);
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.TEN);
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.save(requestDto));

        assertEquals(ProductErrorMessage.HAS_BLANK_PRODUCT_PARAMETER, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenNameOfDtoIsEmpty() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.TEN);
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.save(requestDto));

        assertEquals(ProductErrorMessage.HAS_BLANK_PRODUCT_PARAMETER, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenTaxFreePriceOfDtoIsNull() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(null);
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.save(requestDto));

        assertEquals(ProductErrorMessage.HAS_BLANK_PRODUCT_PARAMETER, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenTaxFreePriceOfDtoEqualsZero() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.ZERO);
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.save(requestDto));

        assertEquals(ProductErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenTaxFreePriceOfDtoIsUnderZero() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.valueOf(-50));
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.save(requestDto));

        assertEquals(ProductErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenCategoryIdIsUnderZero() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.TEN);
        when(requestDto.getCategoryId()).thenReturn(-5L);

        Product product = mock(Product.class);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.save(requestDto));

        assertEquals(ProductErrorMessage.CATEGORY_ID_MUST_BE_GREATER_THAN_ZERO, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotSaveWhenParameterIsNull() {
        assertThrows(NullPointerException.class, () -> productService.save(null));
    }


    @Test
    void shouldUpdate() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.valueOf(10));
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);
        when(product.getTaxFreePrice()).thenReturn(BigDecimal.valueOf(5));
        Category category = mock(Category.class);

        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);
        when(productEntityService.getByIdWithControl(anyLong())).thenReturn(product);
        when(category.getTax()).thenReturn(BigDecimal.valueOf(10));
        when(productEntityService.save(any())).thenReturn(product);

        ProductSaveAndUpdateResponseDto result = productService.update(1L, requestDto);

        assertEquals(new BigDecimal(5), result.getTaxFreePrice());

    }

    @Test
    void shouldNotUpdateWhenTaxFreePriceOfDtoIsUnderZero() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.valueOf(10));
        when(requestDto.getCategoryId()).thenReturn(1L);

        Product product = mock(Product.class);
        when(product.getTaxFreePrice()).thenReturn(BigDecimal.valueOf(5));
        Category category = mock(Category.class);

        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);
        when(productEntityService.getByIdWithControl(anyLong())).thenReturn(product);
        when(category.getTax()).thenReturn(BigDecimal.valueOf(10));
        when(productEntityService.save(any())).thenReturn(product);

        ProductSaveAndUpdateResponseDto result = productService.update(1L, requestDto);

        assertEquals(new BigDecimal(5), result.getTaxFreePrice());
    }

    @Test
    void shouldNotUpdateWhenCategoryIdIsUnderZero() {
        ProductSaveAndUpdateRequestDto requestDto = mock(ProductSaveAndUpdateRequestDto.class);
        when(requestDto.getName()).thenReturn("name");
        when(requestDto.getTaxFreePrice()).thenReturn(BigDecimal.valueOf(10));
        when(requestDto.getCategoryId()).thenReturn(-1L);

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.update(1L, requestDto));

        assertEquals(ProductErrorMessage.CATEGORY_ID_MUST_BE_GREATER_THAN_ZERO, e.getBaseErrorMessage());

    }

    @Test
    void shouldNotUpdateWhenParametersAreNull() {

        assertThrows(NullPointerException.class, () -> productService.update(null, null));

    }

    @Test
    void shouldDelete() {
        productService.delete(anyLong());
        verify(productEntityService).deleteByIdWithControl(any());
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() {
        doThrow(new InvalidParameterExceptions(GeneralErrorMessage.INVALID_REQUEST)).when(productEntityService).deleteByIdWithControl(anyLong());
        InvalidParameterExceptions result = assertThrows(InvalidParameterExceptions.class, () -> productService.delete(anyLong()));
        verify(productEntityService).deleteByIdWithControl(any());
        assertNotNull(result);
    }

    @Test
    void shouldFindProductsByCategoryId() {
        Product product = mock(Product.class);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productEntityService.findProductsByCategoryId(anyLong())).thenReturn(productList);

        productService.findProductsByCategoryId(anyLong());

        assertEquals(1, productList.size());
    }

    @Test
    void shouldNotFindProductsByCategoryIdWhenCategoryIdDoesNotExist() {

        doThrow(new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND)).when(categoryEntityService).validateEntityExist(anyLong());

        EntityNotFoundExceptions e = assertThrows(EntityNotFoundExceptions.class, () -> productService.findProductsByCategoryId(anyLong()));

        verify(categoryEntityService).validateEntityExist(anyLong());
        assertNotNull(e);
    }

    @Test
    void shouldUpdateProductPrice() {
        BigDecimal bigDecimalFour = new BigDecimal(4);

        Product product = mock(Product.class);
        when(productEntityService.getByIdWithControl(anyLong())).thenReturn(product);
        product.setId(1L);
        product.setTaxFreePrice(BigDecimal.TEN);
        product.setCategoryId(2L);

        Category category = mock(Category.class);
        when(category.getTax()).thenReturn(BigDecimal.valueOf(15));
        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);
        when(productEntityService.save(any())).thenReturn(product);

        ProductSaveAndUpdateResponseDto result = productService.updateProductPrice(1L, bigDecimalFour);

        assertEquals(0, result.getCategoryId());
    }

    @Test
    void shouldNotUpdateProductPriceWhenTaxFreePriceIUnderZero() {
        BigDecimal bigDecimalValue = new BigDecimal(-5);
        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.updateProductPrice(1L, bigDecimalValue));
        assertEquals(ProductErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO, e.getBaseErrorMessage());
    }

    @Test
    void shouldNotUpdateProductPriceWhenProductIdDoesNotExist() {
        doThrow(new InvalidParameterExceptions(GeneralErrorMessage.INVALID_REQUEST)).when(productEntityService).getByIdWithControl(anyLong());
        InvalidParameterExceptions result = assertThrows(InvalidParameterExceptions.class, () -> productService.updateProductPrice(anyLong(), BigDecimal.ONE));
        verify(productEntityService).getByIdWithControl(any());
        assertNotNull(result);
    }

    @Test
    void shouldFindAll() {

        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productEntityService.findAll()).thenReturn(productList);

        List<ProductSaveAndUpdateResponseDto> requestDtoList = productService.findAll();

        assertEquals(1, requestDtoList.size());

    }

    @Test
    void shouldFindAllWhenProductListIsEmpty() {

        List<Product> productList = new ArrayList<>();

        when(productEntityService.findAll()).thenReturn(productList);

        List<ProductSaveAndUpdateResponseDto> requestDtoList = productService.findAll();

        assertEquals(0, requestDtoList.size());
    }

    @Test
    void shouldNotFindAllWhenProductListIsNull() {

        when(productEntityService.findAll()).thenThrow(new EntityNotFoundExceptions(GeneralErrorMessage.ENTITIES_NOT_FOUND));

        EntityNotFoundExceptions e = assertThrows(EntityNotFoundExceptions.class, () -> productService.findAll());

        assertEquals(GeneralErrorMessage.ENTITIES_NOT_FOUND, e.getBaseErrorMessage());
    }

    @Test
    void shouldFindProductsByLastPriceWithTaxBetween() {
        BigDecimal one = BigDecimal.ONE;
        BigDecimal two = BigDecimal.valueOf(2);

        Product product = mock(Product.class);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.findProductsByLastPriceWithTaxBetween(any(), any())).thenReturn(productList);
        List<ProductSaveAndUpdateResponseDto> result = productService.findProductsByLastPriceWithTaxBetween(one, two);
        assertEquals(1,result.size());
    }

    @Test
    void shouldNotFindProductsByLastPriceWithTaxBetweenWhenMinValueGreaterThanMaxValue() {
        BigDecimal mustBeBigValueButNot = BigDecimal.valueOf(2);
        BigDecimal mustBeSmallValueButNot = BigDecimal.ONE;

        InvalidParameterExceptions e = assertThrows(InvalidParameterExceptions.class, () -> productService.findProductsByLastPriceWithTaxBetween(mustBeBigValueButNot, mustBeSmallValueButNot));

        assertEquals(GeneralErrorMessage.INVALID_REQUEST,e.getBaseErrorMessage());
    }

    @Test
    void shouldGetProductCategoryDetails() {
        List<ProductCategoryDetailResult> responseDtoList = new ArrayList<>();

        when(productEntityService.getProductCategoryDetails()).thenReturn(responseDtoList);

        List<ProductCategoryDetailResponseDto> result = productService.getProductCategoryDetails();

        assertEquals(0, result.size());
    }

    @Test
    void shouldPriceRegulator() {
        Category category = mock(Category.class);
        when(category.getTax()).thenReturn(BigDecimal.TEN);

        when(categoryEntityService.getByIdWithControl(anyLong())).thenReturn(category);

        Product product = mock(Product.class);
        when(product.getTaxFreePrice()).thenReturn(BigDecimal.TEN);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.findAll()).thenReturn(productList);

        productService.priceRegulator(1L);
        verify(productEntityService).save(product);
    }

    @Test
    void shouldNotPriceRegulatorWhenCategoryIdNull() {
        assertThrows(NullPointerException.class, () -> productService.priceRegulator(1L));
    }

    @Test
    void shouldNotPriceRegulatorWhenCategoryIdDoesNotExist() {
        doThrow(new InvalidParameterExceptions(GeneralErrorMessage.INVALID_REQUEST)).when(categoryEntityService).getByIdWithControl(anyLong());
        InvalidParameterExceptions result = assertThrows(InvalidParameterExceptions.class, () -> productService.priceRegulator(anyLong()));
        verify(categoryEntityService).getByIdWithControl(any());
        assertNotNull(result);
    }
}