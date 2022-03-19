package com.softtech.softtechspringboot.Exception;

import com.softtech.softtechspringboot.Enum.ErrorEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParameterExceptions extends BusinessException{

    public InvalidParameterExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
