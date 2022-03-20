package com.softtech.softtechspringboot.Exception;

import com.softtech.softtechspringboot.Enum.ErrorEnums.BaseErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException {
    private BaseErrorMessage baseErrorMessage;

    public BusinessException(BaseErrorMessage baseErrorMessage, String className) {
        super(className);
        this.baseErrorMessage = baseErrorMessage;
    }
}
