package com.softtech.softtechspringboot.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<E> implements Serializable {

    private E data;
    private Date responseDate;
    private boolean isSuccess;
    private String message;

    public  GeneralResponse(E data, boolean isSuccess){
        this.data = data;
        this.isSuccess = isSuccess;
        this.responseDate = new Date();
    }

    public static <E> GeneralResponse<E> of(E e){
        return new GeneralResponse<>(e, true);
    }

    public static <E> GeneralResponse<E> error(E e){
        return new GeneralResponse<>(e, false);
    }

    public static <E> GeneralResponse<E> empty(){
        return new GeneralResponse<>(null, true);
    }

    public void setMessages(String message) {
        this.message = message;
    }
}
