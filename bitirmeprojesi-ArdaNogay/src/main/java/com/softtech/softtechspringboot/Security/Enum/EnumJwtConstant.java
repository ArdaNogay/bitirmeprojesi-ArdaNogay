package com.softtech.softtechspringboot.Security.Enum;

public enum EnumJwtConstant {

    BEARER("Bearer ")
    ;

    private String constant;
    EnumJwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
