package com.softtech.softtechspringboot.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserSaveAndUpdateRequestDto {

    private String userName;
    private String password;
    private String name;
    private String surname;
}
