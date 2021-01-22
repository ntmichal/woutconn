package workoutconnection.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Meal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class MealInfoServiceTest {

    @Autowired
    private IMealInfoService mealInfoService;

    @Test
    void getMealByDate_then_return_two_meals(){
        List<Map<String,Object>> mealsLinkMap = mealInfoService
                .getMealByDate(1, LocalDate.now());

        assertEquals(2, mealsLinkMap.size());

        Map<String, Object> mealsLinksMap1 = new HashMap<>();
        mealsLinksMap1.put("id", 1);
        mealsLinksMap1.put("name", "Sniadanie");
        mealsLinksMap1.put("link", "/api/meal/1/id/1");

        assertEquals(mealsLinkMap.get(0).equals(mealsLinksMap1),true);

        Map<String, Object> mealsLinksMap2 = new HashMap<>();
        mealsLinksMap2.put("id", 2);
        mealsLinksMap2.put("name", "Sniadanie2");
        mealsLinksMap2.put("link", "/api/meal/1/id/2");

        assertEquals(mealsLinkMap.get(1).equals(mealsLinksMap2),true);

    }


    @Test
    void getMeal() {
        List<ProductDto> mealProductsList = mealInfoService.getMealProducts(1,1);

        assertEquals(9, mealProductsList.size());
    }
}