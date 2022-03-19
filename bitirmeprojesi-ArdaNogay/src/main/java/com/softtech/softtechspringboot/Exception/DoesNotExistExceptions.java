package com.softtech.softtechspringboot.Exception;

import com.softtech.softtechspringboot.Enum.ErrorEnums.BaseErrorMessage;
import com.softtech.softtechspringboot.Exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DoesNotExistExceptions extends BusinessException {

    public DoesNotExistExceptions(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
