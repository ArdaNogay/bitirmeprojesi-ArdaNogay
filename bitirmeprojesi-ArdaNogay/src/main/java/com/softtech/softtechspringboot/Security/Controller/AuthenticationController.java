package com.softtech.softtechspringboot.Security.Controller;

import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Security.Dto.LoginRequestDto;
import com.softtech.softtechspringboot.Security.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        String token = authenticationService.login(loginRequestDto);

        return ResponseEntity.ok(GeneralResponse.of(token));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto){

        UserSaveAndUpdateRequestDto requestDto =authenticationService.register(userSaveAndUpdateRequestDto);

        return ResponseEntity.ok(GeneralResponse.of(requestDto));
    }
}
