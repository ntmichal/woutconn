package workoutconnection.service;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import workoutconnection.dto.MealDto;
import workoutconnection.dto.MealInsertDto;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Meal;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
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
    void inserMeal(){

        List<MealInsertDto.ProductPath> productPathList = new ArrayList<>();
        productPathList.add(new MealInsertDto.ProductPath(1l,10));
        productPathList.add(new MealInsertDto.ProductPath(2l,20));
        productPathList.add(new MealInsertDto.ProductPath(3l,30));

        MealInsertDto mealInsertDto = new MealInsertDto()
                .setName("Breakfast")
                .setMealDate(LocalDate.now())
                .setProductPaths(productPathList);

        MealDto meal = mealInfoService.insertMeal(mealInsertDto, 1);



        assertNotNull(meal.getId());
        assertEquals(meal.getName(),mealInsertDto.getName());
        assertEquals(meal.getMealDate(), mealInsertDto.getMealDate());

        List<String> productsList = mealInfoService
                .getMealProducts(meal.getId(),1);

        assertEquals(productPathList.size(),productsList.size());

    }

    @Test
    void getMealByDate_then_return_two_meals(){
        List<Map<String,Object>> mealsLinkMap = mealInfoService
                .getMealByDate(1, LocalDate.now());

        assertEquals(3, mealsLinkMap.size());

        Map<String, Object> mealsLinksMap1 = new HashMap<>();
        mealsLinksMap1.put("id", 1);
        mealsLinksMap1.put("name", "Sniadanie");
        mealsLinksMap1.put("path", "/api/meal/1/id/1/product/list");

        assertEquals(mealsLinkMap.get(0).equals(mealsLinksMap1),true);

        Map<String, Object> mealsLinksMap2 = new HashMap<>();
        mealsLinksMap2.put("id", 2);
        mealsLinksMap2.put("name", "Sniadanie2");
        mealsLinksMap2.put("path", "/api/meal/1/id/2/product/list");

        assertEquals(mealsLinkMap.get(1).equals(mealsLinksMap2),true);

    }


    @Test
    void getMeal() {
        List<String> mealProductsList = mealInfoService.getMealProducts(1,1);

        assertEquals(9, mealProductsList.size());
    }




}