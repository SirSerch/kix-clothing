package com.ironhack.kix.product.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.kix.product.service.clients.ImageClient;
import com.ironhack.kix.product.service.clients.SearchClient;
import com.ironhack.kix.product.service.models.Product;
import com.ironhack.kix.product.service.models.dto.ProductDTO;
import com.ironhack.kix.product.service.repositories.ProductRepository;
import org.assertj.core.util.Streams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    ImageClient imageClient;

    @MockBean
    SearchClient searchClient;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        product = new Product();
        productDTO = new ProductDTO();
        productDTO.setProductDescription("Product");
        productDTO.setProductName("Product");
        productDTO.setProductPrice(new BigDecimal("20.99"));
        productDTO.setProductImages(Stream.of("imagen").collect(Collectors.toList()));
        when(productRepository.findAll()).thenReturn(Stream.of(product).collect(Collectors.toList()));
        when(productRepository.save(any(Product.class))).thenReturn(product);
    }

    @Test
    void createProduct() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void indexProduct() {
    }

    @Test
    void deleteIndexProduct() {
    }

    @Test
    void searchProductsByImage() {
    }
}