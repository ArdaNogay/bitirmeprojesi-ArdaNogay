package com.softtech.softtechspringboot.Controller;


import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Dto.ProductCategoryDetailResponseDto;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateResponseDto;
import com.softtech.softtechspringboot.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(tags = "Product Controller")
    @GetMapping
    public ResponseEntity findAll(){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/{id}")
    public ResponseEntity findProductsByCategoryId(@PathVariable("id") Long id){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findProductsByCategoryId(id);
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/between")
    public ResponseEntity filterByPrice(@RequestParam("min") BigDecimal smallPrice, @RequestParam("max") BigDecimal bigPrice){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findProductsByLastPriceWithTaxBetween(smallPrice, bigPrice);
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/details")
    public ResponseEntity getProductCategoryDetails(){
        List<ProductCategoryDetailResponseDto> productCategoryDetails = productService.getProductCategoryDetails();
        return ResponseEntity.ok(GeneralResponse.of(productCategoryDetails));
    }

    @Operation(tags = "Product Controller")
    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto){
        ProductSaveAndUpdateResponseDto saveResponseDto = productService.save(productSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(saveResponseDto));
    }

    @Operation(tags = "Product Controller")
    @Validated
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto ,@PathVariable("id") Long id){
        ProductSaveAndUpdateResponseDto updateResponseDto = productService.update(id,productSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(updateResponseDto));
    }

    @Operation(tags = "Product Controller")
    @Validated
    @PatchMapping("/{id}")
    public ResponseEntity updatePrice(@PathVariable("id")Long id , @RequestParam("tax-free-price") BigDecimal taxFreePrice){
        ProductSaveAndUpdateResponseDto updateResponseDto = productService.updateProductPrice(id,taxFreePrice);
        return ResponseEntity.ok(GeneralResponse.of(updateResponseDto));
    }

    @Operation(tags = "Product Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.ok(GeneralResponse.empty());
    }

}
