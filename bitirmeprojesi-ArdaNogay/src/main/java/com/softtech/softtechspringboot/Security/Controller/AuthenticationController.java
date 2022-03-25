package com.softtech.softtechspringboot.Security.Controller;

import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Security.Dto.LoginRequestDto;
import com.softtech.softtechspringboot.Security.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            tags = "Authentication Controller",
            description = "Allows registered user to login.",
            summary = "User login",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "AuthenticationService",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategorySaveAndUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Login with registered user example1",
                                                    summary = "Example registered user 1",
                                                    description = "Login to the system with registered user",
                                                    value = "{\n" +
                                                            "  \"userName\": \"admin\",\n" +
                                                            "  \"password\": \"softtech\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "Login with registered user: example2",
                                                    summary = "Example registered user 2",
                                                    description = "Login to the system with registered user",
                                                    value = "{\n" +
                                                            "  \"userName\": \"final\",\n" +
                                                            "  \"password\": \"project\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        String token = authenticationService.login(loginRequestDto);

        return ResponseEntity.ok(GeneralResponse.of(token));
    }

    @Operation(
            tags = "Authentication Controller",
            description = "Creates a new user register.",
            summary = "User register",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "AuthenticationService",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CategorySaveAndUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Create a new user register 1",
                                                    summary = "Example user register 1",
                                                    description = "Register to create the user",
                                                    value = "{\n" +
                                                            "  \"userName\": \"CeyhanArdaN\",\n" +
                                                            "  \"password\": \"softtech\",\n" +
                                                            "  \"name\": \"Ceyhan Arda\",\n" +
                                                            "  \"surname\": \"Nogay\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "Create a new user register 2",
                                                    summary = "Example user register 2",
                                                    description = "Register to create the user",
                                                    value = "{\n" +
                                                            "  \"userName\": \"ArdaN\",\n" +
                                                            "  \"password\": \"softtech\",\n" +
                                                            "  \"name\": \"Ceyhan Arda\",\n" +
                                                            "  \"surname\": \"Nogay\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto){

        UserSaveAndUpdateRequestDto requestDto =authenticationService.register(userSaveAndUpdateRequestDto);

        return ResponseEntity.ok(GeneralResponse.of(requestDto));
    }
}
