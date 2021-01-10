package workoutconnection.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MealControllerTest {


    @Autowired
    private MockMvc mockMvc;

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
}