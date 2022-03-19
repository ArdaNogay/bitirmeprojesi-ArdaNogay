package com.softtech.softtechspringboot.Service;

import com.softtech.softtechspringboot.Converter.ProductMapper;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateResponseDto;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.Category;
import com.softtech.softtechspringboot.Entity.Product;
import com.softtech.softtechspringboot.Enum.ErrorEnums.GeneralErrorMessage;
import com.softtech.softtechspringboot.Enum.ErrorEnums.ProductErrorMessage;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import com.softtech.softtechspringboot.Service.EntityService.CategoryEntityService;
import com.softtech.softtechspringboot.Service.EntityService.ProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductEntityService productEntityService;
    private final CategoryEntityService categoryEntityService;

    public ProductSaveAndUpdateResponseDto save(ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto){
        mandatoryFieldCheck(productSaveAndUpdateRequestDto);
        priceValidation(productSaveAndUpdateRequestDto);
        ProductSaveAndUpdateResponseDto responseDto = taxConfigurator(productSaveAndUpdateRequestDto);
        Product product = ProductMapper.INSTANCE.convertToProduct(responseDto);
        productEntityService.save(product);
        ProductSaveAndUpdateResponseDto saveResponseDto = ProductMapper.INSTANCE.convertToProductSaveAndUpdateResponseDto(product);
        return saveResponseDto;
    }

    public ProductSaveAndUpdateResponseDto update(Long id , ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto){
        mandatoryFieldCheck(productSaveAndUpdateRequestDto);
        priceValidation(productSaveAndUpdateRequestDto);
        productEntityService.entityExistValidation(id);
        ProductSaveAndUpdateResponseDto productSaveAndUpdateDto = taxConfigurator(productSaveAndUpdateRequestDto);
        Product product = productUpdateMapping(id, productSaveAndUpdateDto);
        ProductSaveAndUpdateResponseDto updateResponseDto = ProductMapper.INSTANCE.convertToProductSaveAndUpdateResponseDto(product);
        return updateResponseDto;
    }

    public ProductSaveAndUpdateResponseDto productPriceUpdate(Long id, Double taxFreePrice){
        priceValidation(taxFreePrice);
        Product product = productEntityService.getByIdWithControl(id);
        ProductSaveAndUpdateRequestDto updateRequestDto = ProductMapper.INSTANCE.convertToProductSaveAndUpdateRequestDto(product);
        updateRequestDto.setTaxFreePrice(taxFreePrice);
        ProductSaveAndUpdateResponseDto productSaveAndUpdateDto = taxConfigurator(updateRequestDto);
        product = productUpdateMapping(id, productSaveAndUpdateDto);
        ProductSaveAndUpdateResponseDto updateResponseDto = ProductMapper.INSTANCE.convertToProductSaveAndUpdateResponseDto(product);
        return updateResponseDto;
    }

    public void delete(Long id){
        Product product = productEntityService.getById(id);
        productEntityService.delete(product);
    }

    public List<ProductSaveAndUpdateResponseDto> findProductsByCategoryId(Long id){
        List<Product> productListByCategoryId = productEntityService.findProductsByCategoryId(id);
        List<ProductSaveAndUpdateResponseDto> requestDtoList = ProductMapper.INSTANCE
                .convertToProductSaveAndUpdateResponseDtoList(productListByCategoryId);
        return  requestDtoList;
    }

    public List<ProductSaveAndUpdateResponseDto> findAll(){
        List<Product> productList = productEntityService.findAll();
        List<ProductSaveAndUpdateResponseDto> requestDtoList = ProductMapper.INSTANCE
                .convertToProductSaveAndUpdateResponseDtoList(productList);
        return requestDtoList;
    }

    private ProductSaveAndUpdateResponseDto taxConfigurator(ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto) {
        Double tax = taxCaller(productSaveAndUpdateRequestDto.getCategoryId());
        Double taxFreePrice = productSaveAndUpdateRequestDto.getTaxFreePrice();
        Double lastPriceWithTax = taxFreePrice + ((tax/100) * taxFreePrice);

        ProductSaveAndUpdateResponseDto productSaveAndUpdateDto = ProductMapper.INSTANCE.convertToProductSaveAndUpdateResponseDto(productSaveAndUpdateRequestDto);
        productSaveAndUpdateDto.setLastPriceWithTax(lastPriceWithTax);
        productSaveAndUpdateDto.setTaxPrice(lastPriceWithTax-taxFreePrice);
        return productSaveAndUpdateDto;
    }

    private Double taxCaller(Long categoryId) {
        Category category = categoryEntityService.getByIdWithControl(categoryId);
        Double tax = category.getTax();
        return tax;
    }
    

    private Product productUpdateMapping(Long id, ProductSaveAndUpdateResponseDto productSaveAndUpdateDto) {
        Product product = productEntityService.getById(id);
        product.setName(productSaveAndUpdateDto.getName());
        product.setTaxFreePrice(productSaveAndUpdateDto.getTaxFreePrice());
        product.setCategoryId(productSaveAndUpdateDto.getCategoryId());
        product.setLastPriceWithTax(productSaveAndUpdateDto.getLastPriceWithTax());
        product.setTaxPrice(productSaveAndUpdateDto.getTaxPrice());
        product = productEntityService.save(product);
        return product;
    }

    private void priceValidation(ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto) {
        Double price = productSaveAndUpdateRequestDto.getTaxFreePrice();
        if (price<=0 || price == null){
            throw new InvalidParameterExceptions(ProductErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO);
        }
    }
    private void priceValidation(Double taxFreePrice) {
        Double price = taxFreePrice;
        if (price<=0 || price == null){
            throw new InvalidParameterExceptions(ProductErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    private void mandatoryFieldCheck(ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto) {
        String name = productSaveAndUpdateRequestDto.getName();
        Double taxFreePrice = productSaveAndUpdateRequestDto.getTaxFreePrice();
        Long categoryId = productSaveAndUpdateRequestDto.getCategoryId();
        if(name == null || taxFreePrice == null || categoryId == null){
            throw new InvalidParameterExceptions(ProductErrorMessage.HAS_BLANK_PRODUCT_PARAMETER);
        }
        else if (!StringUtils.hasText(name)){
            throw new InvalidParameterExceptions(ProductErrorMessage.HAS_BLANK_PRODUCT_PARAMETER);
        }
        else if (categoryId<0){
            throw new InvalidParameterExceptions(ProductErrorMessage.CATEGORY_ID_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    public void priceRegulator(Long categoryId){
        Double tax = taxCaller(categoryId);
        List<Product> productList = productEntityService.findAll();
        for (Product product : productList) {
            Double taxFreePrice = product.getTaxFreePrice();
            Double lastPriceWithTax = taxFreePrice + ((tax/100) * taxFreePrice);
            product.setLastPriceWithTax(lastPriceWithTax);
            product.setTaxPrice(lastPriceWithTax-taxFreePrice);
            productEntityService.save(product);
        }
    }

}
