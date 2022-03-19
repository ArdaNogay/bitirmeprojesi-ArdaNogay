package com.softtech.softtechspringboot.Controller;

import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Dto.UserDeleteDto;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity findAll(){
        List<UserSaveAndUpdateRequestDto> requestDtoList = userService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(requestDtoList));
    }

    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto){
        UserSaveAndUpdateRequestDto saveRequestDto = userService.save(userSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(saveRequestDto));
    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto ,@PathVariable("id") Long id){
        UserSaveAndUpdateRequestDto updateRequestDto = userService.update(id,userSaveAndUpdateRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(updateRequestDto));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody UserDeleteDto userDeleteDto){
        userService.delete(userDeleteDto);
        return ResponseEntity.ok(GeneralResponse.empty());
    }

}
