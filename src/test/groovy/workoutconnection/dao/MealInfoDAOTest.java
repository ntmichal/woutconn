package workoutconnection.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import workoutconnection.entities.Meal;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MealInfoDAOTest {


    @Autowired
    private IMealInfoDAO mealInfoDAO;
    final LocalDate todayDate = LocalDate.now();

    @Test
    void getMealByDate() {

        List<Meal> mealList = (List<Meal>) mealInfoDAO
                .getMealByDate(1, todayDate);

        assertEquals(2,mealList.size());
    }
}