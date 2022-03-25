package com.softtech.softtechspringboot.Controller;

import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(tags = "User Controller", description = "All registered users are shown in the list.", summary = "Returns all users")
    @GetMapping
    public ResponseEntity findAll(){
        List<UserSaveAndUpdateRequestDto> requestDtoList = userService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(requestDtoList));
    }

    @Operation(
            tags = "User Controller",
            description = "Saves a new user according to the information filled.",
            summary = "Saves a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategorySaveAndUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "save a user 1",
                                                    summary = "New User Example 1",
                                                    description = "Saves new user based on filled information",
                                                    value = "{\n" +
                                                            "  \"userName\": \"Softtech\",\n" +
                                                            "  \"password\": \"123e\",\n" +
                                                            "  \"name\": \"Ceyhan Arda\",\n" +
                                                            "  \"surname\": \"Nogay\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "save a user 2",
                                                    summary = "New User Example 2",
                                                    description = "Saves new user based on filled information",
                                                    value = "{\n" +
                                                            "  \"userName\": \"Software\",\n" +
                                                            "  \"password\": \"Engineer\",\n" +
                                                            "  \"name\": \"In\",\n" +
                                                            "  \"surname\": \"Softtech\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto){
        UserSaveAndUpdateRequestDto saveRequestDto = userService.save(userSaveAndUpdateRequestDto);

        WebMvcLinkBuilder linkFindAll = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        EntityModel entityModel = EntityModel.of(saveRequestDto);
        entityModel.add(linkFindAll.withRel("Find all users"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        return ResponseEntity.ok(GeneralResponse.of(mappingJacksonValue));
    }

    @Operation(
            tags = "User Controller",
            description = "Updates a registered user based on the information filled in.",
            summary = "Updates a registered user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategorySaveAndUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update a user",
                                                    summary = "User Example",
                                                    description = "Updates new user based on filled information",
                                                    value = "{\n" +
                                                            "  \"userName\": \"Softtech\",\n" +
                                                            "  \"password\": \"123e\",\n" +
                                                            "  \"name\": \"Ceyhan Arda\",\n" +
                                                            "  \"surname\": \"Nogay\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @Validated
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto ,@Parameter(required = true, description = "There must be a user with an ID to fill")@PathVariable("id") Long id){
        UserSaveAndUpdateRequestDto updateRequestDto = userService.update(id,userSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(updateRequestDto));
    }

    @Operation(
            tags = "User Controller",
            description = "Deletes a registered user based on the filled Id",
            summary = "Deletes a registered user"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Parameter(required = true, description = "There must be a user with an ID to fill")@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.ok(GeneralResponse.empty());
    }

}
