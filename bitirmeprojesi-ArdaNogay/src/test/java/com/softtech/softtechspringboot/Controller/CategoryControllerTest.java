package com.softtech.softtechspringboot.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.softtechspringboot.BaseTest;
import com.softtech.softtechspringboot.Config.H2TestProfileJPAConfig;
import com.softtech.softtechspringboot.Dto.CategorySaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Enum.CategoryType;
import com.softtech.softtechspringboot.SofttechSpringBootApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {SofttechSpringBootApplication.class, H2TestProfileJPAConfig.class})
class CategoryControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/categories";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void save() throws Exception {
        CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto = CategorySaveAndUpdateRequestDto.builder()
                .categoryType(CategoryType.OTHER)
                .tax(BigDecimal.valueOf(18))
                .build();

        String content = objectMapper.writeValueAsString(categorySaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void update() throws Exception {
        CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto = CategorySaveAndUpdateRequestDto.builder()
                .categoryType(CategoryType.CLOTHING)
                .tax(BigDecimal.valueOf(20))
                .build();

        String content = objectMapper.writeValueAsString(categorySaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
    @Test
    void shouldNotUpdateWhenTaxIsLessThanZero() throws Exception {
        CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto = CategorySaveAndUpdateRequestDto.builder()
                .categoryType(CategoryType.TECHNOLOGY)
                .tax(BigDecimal.valueOf(-15))
                .build();

        String content = objectMapper.writeValueAsString(categorySaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void shouldNotUpdateWhenCategoryTypeDoesNotExist() throws Exception {
        CategorySaveAndUpdateRequestDto categorySaveAndUpdateRequestDto = CategorySaveAndUpdateRequestDto.builder()
                .categoryType(null)
                .tax(BigDecimal.valueOf(15))
                .build();

        String content = objectMapper.writeValueAsString(categorySaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void deleteTest() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/1").content("1").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void shouldNoDeleteWhenIdDoesNotExist() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/9999").content("9999").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }
}