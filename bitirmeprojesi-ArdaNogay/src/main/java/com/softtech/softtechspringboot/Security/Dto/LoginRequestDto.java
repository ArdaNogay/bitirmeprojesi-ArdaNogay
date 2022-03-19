package com.softtech.softtechspringboot.Security.Dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String userName;    //Todo: userName ---> username olmalı !!
    private String password;
}
