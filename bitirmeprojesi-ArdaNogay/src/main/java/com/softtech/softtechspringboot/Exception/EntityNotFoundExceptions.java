package com.softtech.softtechspringboot.Exception;

import com.softtech.softtechspringboot.Enum.ErrorEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptions extends BusinessException{

    public EntityNotFoundExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
