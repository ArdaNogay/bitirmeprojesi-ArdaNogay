package com.softtech.softtechspringboot.Exception.GeneralExceptionHandler;

import com.softtech.softtechspringboot.Dto.GeneralResponse;
import com.softtech.softtechspringboot.Exception.DoesNotExistExceptions;
import com.softtech.softtechspringboot.Exception.DuplicateEntityExceptions;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, message, description, HttpStatus.INTERNAL_SERVER_ERROR.value());
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse);
        generalResponse.setMessages(message);
        return new ResponseEntity<>(generalResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDoesNotExistExceptions(DoesNotExistExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,message,detailMessage,HttpStatus.NOT_ACCEPTABLE.value());
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse);
        generalResponse.setMessages(detailMessage);
        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateEntityExceptions(DuplicateEntityExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,message,detailMessage,HttpStatus.CONFLICT.value());
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse);
        generalResponse.setMessages(detailMessage);
        return new ResponseEntity<>(generalResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleEntityNotFoundExceptions(EntityNotFoundExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage()+" --> "+ex.getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,message,detailMessage,HttpStatus.NOT_FOUND.value());
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse);
        generalResponse.setMessages(detailMessage);
        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidParameterExceptions(InvalidParameterExceptions ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,message,detailMessage,HttpStatus.BAD_REQUEST.value());
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse);
        generalResponse.setMessages(detailMessage);
        return new ResponseEntity<>(generalResponse, HttpStatus.BAD_REQUEST);
    }

}
