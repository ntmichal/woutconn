package workoutconnection.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import workoutconnection.entities.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    static final int ONE_PRODUCT = 1;
    static final int MANY_PRODUCT = 4;
    static final String PRODUCT_NOT_FOUND = "-123";
    static final String PRODUCT_ONE_FOUND = "dark chocolate";
    static final String PRODUCT_MANY_FOUND = "milk";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void anyProductNotFound() throws Exception{
        mockMvc.perform(get("/api/product?name="+PRODUCT_NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void findProduct() throws Exception{

        mockMvc.perform(get("/api/product?name="+PRODUCT_ONE_FOUND))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findOneProduct() throws Exception {
        MvcResult mock = mockMvc.perform(get("/api/product?name="+PRODUCT_ONE_FOUND))
                .andDo(print())
                .andReturn();
        List<Product> productFromDatabase  = objectMapper.readValue(
                mock.getResponse().getContentAsString(),
                new TypeReference<List<Product>>() {}
        );
    }

    @Test
    void findManyProduct() throws Exception {
        MvcResult mock = mockMvc.perform(get("/api/product?name="+PRODUCT_MANY_FOUND))
                .andDo(print())
                .andReturn();
        List<Product> productsFromDatabase = objectMapper.readValue(
                mock.getResponse().getContentAsString(),
                new TypeReference<List<Product>>() {});
        assertEquals(productsFromDatabase.size(), MANY_PRODUCT);
    }
}