package com.softtech.softtechspringboot.Exception;

import com.softtech.softtechspringboot.Enum.ErrorEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntityExceptions extends BusinessException{

    public DuplicateEntityExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
