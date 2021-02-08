package workoutconnection.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import workoutconnection.dto.MealInsertDto;
import workoutconnection.dto.ProductDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class MealControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    final static int USER_ID_WHEN_STATUS_IS_OK = 1;
    final static int USER_ID_WHEN_IS_DIFFERENT_WITH_USER_ID = 2;
    final static LocalDate TODAY_DATE = LocalDate.now();
    final static LocalDate DATE_MEAL_NOT_FOUND = LocalDate.of(2030,02,01);
    static final String USER_NAME_WITH_ADMIN_ROLE = "admin";
    static final String USER_PASSWORD_WITH_USER_ROLE = "admin";

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
    void getMealByUserIdAndDate_then_is_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/meal/"+USER_ID_WHEN_STATUS_IS_OK+"/list?date="+TODAY_DATE))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getMealByUserIdAndDate_when_userid_from_token_is_different_in_uri_then_is_forbbiden() throws Exception {
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        mockMvc.perform(get("/api/meal/"+USER_ID_WHEN_IS_DIFFERENT_WITH_USER_ID+"/list?date="+TODAY_DATE)
                .header(HttpHeaders.AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void getMealByUserIdAndDate_then_status_is_Ok() throws Exception {
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        mockMvc.perform(get("/api/meal/"+USER_ID_WHEN_STATUS_IS_OK+"/list?date="+TODAY_DATE)
                .header(HttpHeaders.AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getMealByUserIdAndDate_then_status_is_NotFound() throws Exception {
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        mockMvc.perform(get("/api/meal/"+USER_ID_WHEN_STATUS_IS_OK+"/list?date="+DATE_MEAL_NOT_FOUND)
                .header(HttpHeaders.AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getMealProductList_whenUserIdInPathIsDifferentFromTokenUserID_isForbidden() throws Exception {
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        mockMvc.perform(get("/api/meal/3/id/1/product/list")
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isForbidden());
    }


    @Test
    void getMealProductList_isFound() throws Exception {
        String token = "Bearer " + getAccessToken(USER_NAME_WITH_ADMIN_ROLE
                ,USER_PASSWORD_WITH_USER_ROLE);

        MvcResult mvcResult = mockMvc.perform(get("/api/meal/1/id/1/product/list")
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andReturn();

        List<String> pathsToMealProducts =
                        objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                                new TypeReference<List<String>>() {});


        assertEquals(pathsToMealProducts.size(), 9);
        assertEquals(pathsToMealProducts.get(0),"/api/product/1");
        assertEquals(pathsToMealProducts.get(1),"/api/product/2");
    }

    @Test
    void insertMeal_then_status_is_unauthorized() throws Exception {
        mockMvc.perform(post("/api/meal")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString("{}")))
                    .andExpect(status().isUnauthorized());
    }

    @Test
    void insertMeal_isAuthenticated_thenCreated() throws Exception {
        String token = "Bearer "+ getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        MealInsertDto mealInsertDto = new MealInsertDto();
        mealInsertDto
                .setName("Breakfast")
                .setMealDate(LocalDate.now())
                .insertProductPath(new MealInsertDto.ProductPath(1l,10))
                .insertProductPath(new MealInsertDto.ProductPath(2l,20))
                .insertProductPath(new MealInsertDto.ProductPath(3l,30));


        MvcResult mvcResult = mockMvc.perform(post("/api/meal")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mealInsertDto)))
                .andExpect(status().isCreated())
                .andReturn();

        String createdLocationFromResponse = mvcResult.getResponse().getHeader("Location");

        assertEquals("3",createdLocationFromResponse);

        MvcResult productListResponeAfterInsertMeal =
                mockMvc.perform(
                        get("/api/meal/1/id/"+createdLocationFromResponse+"/product/list")
                        .header(HttpHeaders.AUTHORIZATION, token))
                        .andExpect(status().isOk())
                .andReturn();

        List<String> insertedMealProducts =
                objectMapper.readValue(productListResponeAfterInsertMeal.getResponse()
                        .getContentAsString(), new TypeReference<List<String>>() {});

        assertEquals(insertedMealProducts.size(), mealInsertDto.getProductPaths().size());

    }


    @Test
    void deleteMeal_notFound() throws Exception {
        String token = "Bearer "+ getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        mockMvc.perform(
                delete("/api/meal/-1")
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMeal_deleted() throws Exception {
        String token = "Bearer "+ getAccessToken(USER_NAME_WITH_ADMIN_ROLE,USER_PASSWORD_WITH_USER_ROLE);

        mockMvc.perform(
                delete("/api/meal/1")
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk());
    }
}