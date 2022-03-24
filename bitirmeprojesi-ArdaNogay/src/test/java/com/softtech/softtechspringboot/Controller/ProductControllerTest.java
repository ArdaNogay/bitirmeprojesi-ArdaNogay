package com.softtech.softtechspringboot.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.softtechspringboot.BaseTest;
import com.softtech.softtechspringboot.Config.H2TestProfileJPAConfig;
import com.softtech.softtechspringboot.Dto.ProductSaveAndUpdateRequestDto;
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
class ProductControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findProductsByCategoryId() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotFindProductsWhenCategoryIdDoesNotExist() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1999").content("1999L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void filterByPrice() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/between?min=20&max=1000").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotFilterByPriceWhenInputsInvalid() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/between?min=100&max=10").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void getProductCategoryDetails() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/details").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void save() throws Exception {
        ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto = ProductSaveAndUpdateRequestDto.builder()
                .name("test ürün")
                .taxFreePrice(BigDecimal.TEN)
                .categoryId(2L)
                .build();

        String content = objectMapper.writeValueAsString(productSaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotSaveWhenParametersAreNull() throws Exception {
        ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto = ProductSaveAndUpdateRequestDto.builder().build();

        String content = objectMapper.writeValueAsString(productSaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void update() throws Exception {
        ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto = ProductSaveAndUpdateRequestDto.builder()
                .name("Cam Bezi")
                .taxFreePrice(BigDecimal.valueOf(15))
                .categoryId(5L)
                .build();

        String content = objectMapper.writeValueAsString(productSaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH + "/4").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotUpdateWhenTaxFreePrıceLessThanZero() throws Exception {
        ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto = ProductSaveAndUpdateRequestDto.builder()
                .name("Cam Bezi")
                .taxFreePrice(BigDecimal.valueOf(-15))
                .categoryId(5L)
                .build();


        String content = objectMapper.writeValueAsString(productSaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH + "/4").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void shouldNotUpdateWhenDtoParametersAreNull() throws Exception {
        ProductSaveAndUpdateRequestDto productSaveAndUpdateRequestDto = ProductSaveAndUpdateRequestDto.builder()
                .build();

        String content = objectMapper.writeValueAsString(productSaveAndUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH + "/4").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void updatePrice() throws Exception {
        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/4?tax-free-price=4").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotUpdatePriceWhenTaxFreePrıceLessThanZero() throws Exception {
        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/4?tax-free-price=-4").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void shouldNotUpdatePriceWhenProductIdDoesNotExist() throws Exception {
        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/4?tax-free-price=4").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void deleteTest() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
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