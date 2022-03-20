package com.softtech.softtechspringboot.Controller;

import com.softtech.softtechspringboot.Dto.*;
import com.softtech.softtechspringboot.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto){
        CategorySaveAndUpdateRequestDto saveRequestDto = categoryService.save(categorySaveAndUpdateRequestDto);

        WebMvcLinkBuilder linkUpdate = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).update(saveRequestDto));
        EntityModel entityModel = EntityModel.of(saveRequestDto);
        entityModel.add(linkUpdate.withRel("Update Category"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        return ResponseEntity.ok(GeneralResponse.of(mappingJacksonValue));
    }

    @Validated
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto){
        CategorySaveAndUpdateRequestDto updateRequestDto = categoryService.update(categorySaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(updateRequestDto));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody CategoryDeleteDto categoryDeleteDto){
        categoryService.delete(categoryDeleteDto);
        return ResponseEntity.ok(GeneralResponse.empty());
    }
}
