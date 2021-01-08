package workoutconnection.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MealControllerTest {


    @Autowired
    private MockMvc mockMvc;

    final static int USER_ID_WHEN_STATUS_IS_OK = 1;
    @Test
    void getMealByUserIdAndDate_then_status_is_Ok() throws Exception {
        mockMvc.perform(get("/api/meal/"+USER_ID_WHEN_STATUS_IS_OK+"/list?date=2021-01-08"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
}