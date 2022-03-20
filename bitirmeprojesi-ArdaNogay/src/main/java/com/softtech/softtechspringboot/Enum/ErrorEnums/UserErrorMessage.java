package com.softtech.softtechspringboot.Enum.ErrorEnums;

public enum UserErrorMessage implements BaseErrorMessage{
    USER_NOT_FOUND_USERNAME("Entities Could Not Found", "There are not any saved user with this user name.", "bitirme3-100-404"),
    USER_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved user with this id.", "bitirme3-101-404"),
    HAS_DUPLICATE_USER_USERNAME("Encounter A Conflict", "This name is already used for another user.", "bitirme3-200-409"),
    HAS_BLANK_USERNAME_PARAMETER("Encounter a Blank Parameter", "User name parameter can not be blank", "bitirme3-203-400"),;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    UserErrorMessage(String message, String detailMessage, String errorCode){
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

    public String toString(){
        return this.message;
    }
}
