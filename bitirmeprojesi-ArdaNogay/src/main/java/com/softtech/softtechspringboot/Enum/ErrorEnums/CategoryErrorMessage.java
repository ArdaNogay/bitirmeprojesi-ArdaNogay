package com.softtech.softtechspringboot.Enum.ErrorEnums;

public enum CategoryErrorMessage implements BaseErrorMessage{

    CATEGORY_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved category with this id.", "bitirme3-001-404"),
    TAX_MUST_BE_ZERO_OR_GREATER("Encounter A Conflict", "Tax value must take a value of zero or greater..", "bitirme3-100-409"),
    HAS_DUPLICATE_CATEGORY_TYPE("Encounter A Conflict", "There is  already saved category with this category type!", "bitirme3-100-409"),
    ;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    CategoryErrorMessage(String message, String detailMessage, String errorCode){
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
