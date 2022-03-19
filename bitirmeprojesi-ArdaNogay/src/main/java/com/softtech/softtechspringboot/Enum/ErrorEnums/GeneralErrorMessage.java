package com.softtech.softtechspringboot.Enum.ErrorEnums;

public enum GeneralErrorMessage implements BaseErrorMessage{
    ENTITY_NOT_FOUND("Entity not found!", "Entity could not find with this id.","bitirme-404"),
    ENTITIES_NOT_FOUND("Entities Could Not Found", "There are not any saved entities.","bitirme-2-404"),
    INVALID_REQUEST("Invalid parameter", "The request sent with parameters is incorrect.","bitirme-3-400"),
    INTERNAL_SERVER_ERROR("Encounter internal server", "Server encountered an unexpected condition that prevented it from fulfilling the request","bitirme-4-500"),
    ;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    GeneralErrorMessage(String message, String detailMessage ,String errorCode) {
        this.message = message;
        this.detailMessage = detailMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
