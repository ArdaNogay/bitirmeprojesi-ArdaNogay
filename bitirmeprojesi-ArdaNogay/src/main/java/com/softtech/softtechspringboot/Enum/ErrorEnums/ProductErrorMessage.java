package com.softtech.softtechspringboot.Enum.ErrorEnums;

public enum ProductErrorMessage implements BaseErrorMessage{

    PRODUCT_NOT_FOUND_ID("Entities Could Not Found", "There are not any saved product with this id!", "bitirme3-001-404"),
    CATEGORY_ID_MUST_BE_GREATER_THAN_ZERO("Encounter A Conflict", "The entered id must be greater than zero!", "bitirme3-100-409"),
    PRICE_MUST_BE_GREATER_THAN_ZERO("Encounter A Conflict", "The selling price must take a value greater than zero!", "bitirme3-100-409"),
    HAS_BLANK_PRODUCT_PARAMETER("Encounter a Blank Parameter", "Mandatory fields cannot be left blank!", "bitirme3-203-400"),;

    ;

    private  final String message;
    private  final String detailMessage;
    private  final String errorCode;

    ProductErrorMessage(String message, String detailMessage, String errorCode){
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
