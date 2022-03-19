package com.softtech.softtechspringboot.Controller;


import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateResponseDto;
import com.softtech.softtechspringboot.Service.ProductService;
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

    @GetMapping
    public ResponseEntity findAll(){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findProductsByCategoryId(@PathVariable("id") Long id){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findProductsByCategoryId(id);
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto){
        ProductSaveAndUpdateResponseDto saveResponseDto = productService.save(productSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(saveResponseDto));
    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto ,@PathVariable("id") Long id){
        ProductSaveAndUpdateResponseDto updateResponseDto = productService.update(id,productSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(updateResponseDto));
    }

    @Validated //Todo: requestbody'e çevir
    @PatchMapping("/by/products/{id}/taxfreeprice/{taxFreePrice}")  //todo:ikinci parametrenin pathi nasıl yazılmalı araştır
    public ResponseEntity priceUpdate(@PathVariable("id")Long id ,@PathVariable("taxFreePrice") BigDecimal taxFreePrice){
        ProductSaveAndUpdateResponseDto updateResponseDto = productService.updateProductPrice(id,taxFreePrice);
        return ResponseEntity.ok(GeneralResponse.of(updateResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.ok(GeneralResponse.empty());
    }


}
