package com.softtech.softtechspringboot.Controller;


import com.softtech.softtechspringboot.Dto.*;
import com.softtech.softtechspringboot.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @Operation(tags = "Product Controller", description = "All registered product are shown in the list.", summary = "Returns all products")
    @GetMapping
    public ResponseEntity findAll(){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Operation(tags = "Product Controller", description = "All registered products are shown in the list by category id.", summary = "Returns products with category id")
    @GetMapping("/{id}")
    public ResponseEntity findProductsByCategoryId(@Parameter(required = true, description = "There must be a category with an ID to fill")@PathVariable("id") Long id){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findProductsByCategoryId(id);
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Operation(tags = "Product Controller", description = "Returns all products within the given minimum and maximum final price range.", summary = "Returns all products in the given price range")
    @GetMapping("/between")
    public ResponseEntity filterByPrice(
            @Parameter(required = true, description = "The minimum price value given must be less than the maximum price value")@RequestParam("min") BigDecimal smallPrice,
            @Parameter(required = true, description = "The maximum price value given must be greater than the minimum price value")@RequestParam("max") BigDecimal bigPrice){
        List<ProductSaveAndUpdateResponseDto> responseDtoList = productService.findProductsByLastPriceWithTaxBetween(smallPrice, bigPrice);
        return ResponseEntity.ok(GeneralResponse.of(responseDtoList));
    }

    @Operation(tags = "Product Controller", description = "Returns the table with detailed information by product categories.", summary = "Provides detailed information by product categories")
    @GetMapping("/details")
    public ResponseEntity getProductCategoryDetails(){
        List<ProductCategoryDetailResponseDto> productCategoryDetails = productService.getProductCategoryDetails();
        return ResponseEntity.ok(GeneralResponse.of(productCategoryDetails));
    }

    @Operation(
            tags = "Product Controller",
            description = "Saves a new product according to the information filled.",
            summary = "Saves a new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Products",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategorySaveAndUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "save a product 1",
                                                    summary = "New Product Example 1",
                                                    description = "Saves new product based on filled information",
                                                    value = "{\n" +
                                                            "  \"name\": \"Candle\",\n" +
                                                            "  \"taxFreePrice\": 5,\n" +
                                                            "  \"categoryId\": 6\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "save a product 2",
                                                    summary = "New Product Example 2",
                                                    description = "Saves new product based on filled information",
                                                    value = "{\n" +
                                                            "  \"name\": \"Softtech Sweater\",\n" +
                                                            "  \"taxFreePrice\": 100,\n" +
                                                            "  \"categoryId\": 2\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto){
        ProductSaveAndUpdateResponseDto saveResponseDto = productService.save(productSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(saveResponseDto));
    }

    @Operation(
            tags = "Product Controller",
            description = "Updates a registered product based on the information filled in.",
            summary = "Updates a registered product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Products",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategorySaveAndUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update a product",
                                                    summary = "Product Example",
                                                    description = "Updates new product based on filled information",
                                                    value = "{\n" +
                                                            "  \"name\": \"Ball\",\n" +
                                                            "  \"taxFreePrice\": 60,\n" +
                                                            "  \"categoryId\": 3\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @Validated
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto ,@Parameter(required = true, description = "There must be a product with an ID to fill")@PathVariable("id") Long id){
        ProductSaveAndUpdateResponseDto updateResponseDto = productService.update(id,productSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(updateResponseDto));
    }

    @Operation(
            tags = "Product Controller",
            description = "Updates the price of a registered product according to the information filled in.",
            summary = "Updates the price of a registered product"
    )
    @Validated
    @PatchMapping("/{id}")
    public ResponseEntity updatePrice(@Parameter(required = true, description = "There must be a product with an ID to fill")@PathVariable("id")Long id ,
                                      @RequestParam("tax-free-price") @DecimalMin(value = "0",message = "Tax free price must be greater than zero") BigDecimal taxFreePrice){
        ProductSaveAndUpdateResponseDto updateResponseDto = productService.updateProductPrice(id,taxFreePrice);
        return ResponseEntity.ok(GeneralResponse.of(updateResponseDto));
    }

    @Operation(
            tags = "Product Controller",
            description = "Deletes a registered product based on the filled Id",
            summary = "Deletes a registered product"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Parameter(required = true, description = "There must be a product with an ID to fill")@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.ok(GeneralResponse.empty());
    }

}
