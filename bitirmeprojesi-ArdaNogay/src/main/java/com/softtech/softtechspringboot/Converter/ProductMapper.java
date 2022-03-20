package com.softtech.softtechspringboot.Converter;

import com.softtech.softtechspringboot.Dto.ProductCategoryDetailResponseDto;
import com.softtech.softtechspringboot.Dto.ProductCategoryDetailResult;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateResponseDto;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductSaveAndUpdateRequestDto convertToProductSaveAndUpdateRequestDto(Product product);

    List<ProductSaveAndUpdateResponseDto> convertToProductSaveAndUpdateResponseDtoList(List<Product> productList);

    ProductSaveAndUpdateResponseDto convertToProductSaveAndUpdateResponseDto(ProductSaveAndUpdateRequestDto ProductSaveAndUpdateRequestDto);

    ProductSaveAndUpdateResponseDto convertToProductSaveAndUpdateResponseDto(Product product);

    Product convertToProduct(ProductSaveAndUpdateResponseDto productSaveAndUpdateResponseDto);

    List<ProductCategoryDetailResponseDto> convertToProductCategoryDetailResponseDto(List<ProductCategoryDetailResult> productCategoryDetailResultList);
}
