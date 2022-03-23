package com.softtech.softtechspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softtech.softtechspringboot.Dto.GeneralResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        GeneralResponse generalResponseResponse = getGeneralResponse(result);

        return isSuccess(generalResponseResponse);
    }

    protected GeneralResponse getGeneralResponse(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), GeneralResponse.class);
    }

    private boolean isSuccess(GeneralResponse generalResponse) {
        return generalResponse.isSuccess();
    }

}
