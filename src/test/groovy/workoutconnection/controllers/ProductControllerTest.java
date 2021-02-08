package workoutconnection.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

    static final int ONE_PRODUCT = 1;
    static final int MANY_PRODUCT = 4;
    static final String PRODUCT_NOT_FOUND = "-123";
    static final String PRODUCT_ONE_FOUND = "dark chocolate";
    static final String PRODUCT_MANY_FOUND = "milk";

    static final String USER_NAME_WITH_USER_ROLE = "admin2";
    static final String USER_PASSWORD_WITH_USER_ROLE = "admin";

    static final String USER_NAME_WITH_ADMIN_ROLE = "admin";
    static final String USER_PASSWORD_WITH_ADMIN_ROLE = "admin";

    static final int PRODUCT_ID = 1;
    static final int PRODUCT_ID_NOT_FOUND = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private String getAccessToken(String login, String password) throws Exception {
        String auth = "client:clientsecret";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","password");
        params.add("username",login);
        params.add("password",password);

        MvcResult result = mockMvc.perform(post("/oauth/token")
                    .params(params)
                    .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString(auth.getBytes()))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();

    }

    @Test
    void getProductById_isNotFound() throws Exception {
        mockMvc.perform(get("/api/product/"+PRODUCT_ID_NOT_FOUND))
                .andExpect(status().isNotFound());

    }

    @Test
    void getProductById_isOk() throws Exception {
        MvcResult mockResult = mockMvc.perform(get("/api/product/"+PRODUCT_ID))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto productDto =
                objectMapper
                        .readValue(mockResult.getResponse().getContentAsString(),ProductDto.class);
        assertEquals(productDto.getId(), PRODUCT_ID);
    }
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
        assertEquals(productFromDatabase.size(), ONE_PRODUCT);
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

    @Test
    void notAuthenticated_insertNewProduct() throws Exception{
        ProductDto product = new ProductDto();

       mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void isAuthenticated_invaildRole_thenForbridden() throws Exception {
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_USER_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        ProductDto productDto = new ProductDto();

        mockMvc.perform(post("/api/product")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isForbidden());

    }

    @Test
    void isAuthenticated_validRole_thenCreated() throws Exception{
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_ADMIN_ROLE);

        ProductDto productDto = new ProductDto();

        mockMvc.perform(post("/api/product")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteProduct_notAuthenticated() throws Exception {
        mockMvc.perform(delete("/api/product/"+PRODUCT_ID))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }
    @Test
    void deleteProduct_isAuthenticated_validRole_thenNotFound() throws Exception{
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_ADMIN_ROLE);
        mockMvc.perform(delete("/api/product/"+PRODUCT_ID_NOT_FOUND)
                    .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteProduct_isAuthenticated_validRole_thenDeleted() throws Exception{
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_ADMIN_ROLE);
        mockMvc.perform(delete("/api/product/"+PRODUCT_ID)
                    .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk());

    }

}