package ru.kotomore.springbootmarket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.kotomore.springbootmarket.dto.RequestProductDTO;
import ru.kotomore.springbootmarket.models.DesktopFormFactor;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;
import ru.kotomore.springbootmarket.repositories.ProductRepository;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc

public class ProductControllerTest {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void clearDataBase() {
        productRepository.deleteAll();
    }

    @Test
    public void testGetAllProductsByType_ReturnsProductList() throws Exception {
        ProductType productType = ProductType.DESKTOP;
        productRepository.save(createProduct());

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("type", productType.name())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetAllProductsByType_ReturnsNoContent() throws Exception {
        ProductType productType = ProductType.LAPTOP;

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("type", productType.name())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetProductById_ReturnsProduct() throws Exception {
        Long productId = 1L;
        productRepository.save(createProduct());

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", productId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetProductById_ReturnsNotFound() throws Exception {
        Long productId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", productId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testAddProduct_ReturnsCreated() throws Exception {
        Product product = createProduct();
        ProductType productType = product.getProductType();
        RequestProductDTO requestProductDTO = modelMapper.map(product, RequestProductDTO.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .param("productType", productType.name())
                        .content(objectMapper.writeValueAsString(requestProductDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testAddProduct_WithoutProductField_ReturnsBadRequest() throws Exception {
        Product product = createProduct();
        product.setFormFactor(null);
        ProductType productType = product.getProductType();
        RequestProductDTO requestProductDTO = modelMapper.map(product, RequestProductDTO.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .param("productType", productType.name())
                        .content(objectMapper.writeValueAsString(requestProductDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddProduct_ReturnsBadRequest_WhenProductTypeNotProvided() throws Exception {
        Product product = createProduct();
        product.setProductType(null);
        RequestProductDTO requestProductDTO = modelMapper.map(product, RequestProductDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(objectMapper.writeValueAsString(requestProductDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateProduct_ReturnsNotFound() throws Exception {
        Product product = createProduct();
        RequestProductDTO requestProductDTO = modelMapper.map(product, RequestProductDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/products/{productId}", 100500)
                        .content(objectMapper.writeValueAsString(requestProductDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setProductType(ProductType.DESKTOP);
        product.setFormFactor(DesktopFormFactor.DESKTOP);
        product.setCount(15);
        product.setPrice(10000);
        product.setSerialNumber("SerialNumber");
        product.setManufacturer("Manufacturer");
        return product;
    }
}
